package cn.edu.hhstu.areaApplication.controller;

import cn.edu.hhstu.areaApplication.service.IApplicationAuditService;
import cn.edu.hhstu.areaApplication.service.IApplicationSelfService;
import cn.edu.hhstu.areaSystem.service.IDepartmentService;
import cn.edu.hhstu.areaSystem.service.ISysConfigService;
import cn.edu.hhstu.entity.AppAuditConfigEntity;
import cn.edu.hhstu.entity.Excel.ApplicationAuditExcel;
import cn.edu.hhstu.entity.Excel.ApplicationSelfExcel;
import cn.edu.hhstu.pojo.ApplicationAudit;
import cn.edu.hhstu.pojo.Department;
import cn.edu.hhstu.security.entity.UserDetailsEntity;
import cn.edu.hhstu.security.pojo.LoginRole;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import cn.edu.hhstu.utils.WordPoiUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

import static cn.edu.hhstu.utils.WordPoiUtil.downloadReportFile;

@Api(tags = "应用系统备案管理")
@Controller
@RequestMapping("applicationAudit")
public class ApplicationAuditController {

    @Autowired
    private IApplicationAuditService applicationAuditService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private IApplicationSelfService applicationSelfService;

    //列表数据视图
    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String index(Model model) throws Exception {

        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);

        List<Integer> annualList = new ArrayList<>();
        int year = Integer.parseInt(DateFormatUtils.format(new Date(), "yyyy"));
        for (int i = year; i >=2021; i--) {
            annualList.add(i);
        }
        model.addAttribute("annualList",annualList);

        AppAuditConfigEntity appAuditConfig = sysConfigService.getAppAuditConfig();
        if(appAuditConfig.getAppAuditYear() == null){
            appAuditConfig.setAppAuditYear(year);
        }
        if(appAuditConfig.getAppAuditStart() == null){
            appAuditConfig.setAppAuditStart(new Date());
        }
        if(appAuditConfig.getAppAuditEnd() == null){
            appAuditConfig.setAppAuditEnd(new Date());
        }
        model.addAttribute("appAuditConfig",appAuditConfig);

        return "areaApplication/applicationAudit/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('applicationaudit:query')")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "appName",value = "应用名称",required = false,dataType = "String"),
        @ApiImplicitParam(name = "annual",value = "年度",required = false,dataType = "Integer"),
        @ApiImplicitParam(name = "departmentId",value = "部门id",required = false,dataType = "String"),
        @ApiImplicitParam(name = "status",value = "状态",required = false,dataType = "Integer"),
        @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
        @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @GetMapping("/listPage")
    @ResponseBody
    public JsonMsg listPage(String appName, Integer annual,String departmentId,Integer status,String orderByClause, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception {
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("appName",appName);
            map.put("annual",annual);
            map.put("departmentId",departmentId);
            map.put("status",status);
            map.put("orderByClause",orderByClause==null?"departmentId":orderByClause.replace("'",""));
            List<ApplicationAudit> list = applicationAuditService.listPage(map, page, rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }


    //列表数据视图
    @ApiOperation("应用系统年度备案列表视图")
    @GetMapping("/indexAudit")
    public String indexAudit(Model model) throws Exception {
        List<Integer> annualList = new ArrayList<>();
        int year = Integer.parseInt(DateFormatUtils.format(new Date(), "yyyy"));
        for (int i = year; i >=2021; i--) {
            annualList.add(i);
        }
        model.addAttribute("annualList",annualList);

        AppAuditConfigEntity appAuditConfig = sysConfigService.getAppAuditConfig();
        if(appAuditConfig.getAppAuditYear() == null){
            appAuditConfig.setAppAuditYear(year);
        }
        if(appAuditConfig.getAppAuditStart() == null){
            appAuditConfig.setAppAuditStart(new Date());
        }
        if(appAuditConfig.getAppAuditEnd() == null){
            appAuditConfig.setAppAuditEnd(new Date());
        }
        model.addAttribute("appAuditConfig",appAuditConfig);

        return "areaApplication/applicationAudit/indexAudit";
    }

    //列表数据json接口
    @ApiOperation("应用系统年度备案列表数据接口")
    @PreAuthorize("hasAnyAuthority('applicationaudit:auditquery')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @GetMapping("/listAuditPage")
    @ResponseBody
    public JsonMsg listAuditPage(Integer annual, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception {
        try {
            if(annual == null){
                return JsonMsg.resonpse(411,"年度不能为空");
            }

            boolean open=false;
            open = IsAuditOpen(annual);

            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("annual",annual);
            UserDetailsEntity loginUser = UserUtil.getLoginUser();
            map.put("departmentId",loginUser.getDepartmentId());

            List<ApplicationAudit> list = applicationAuditService.listPage(map, page, rows);
            PageInfo pageInfo = new PageInfo(list);

            return JsonMsg.success().add("pageInfo",pageInfo).add("open",open);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("应用系统年度备案编辑视图")
    @ApiImplicitParam(name = "id",value = "应用系统备案id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('applicationaudit:edit')")
    @GetMapping("/edit")
    public String edit(String id,Model model) throws Exception{
        ApplicationAudit detail = applicationAuditService.detail(id);
        model.addAttribute("model",detail);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);

        return "areaApplication/applicationAudit/modify";
    }

    @ApiOperation("应用系统年度备案编辑保存")
    @ApiImplicitParam(name = "entity",value = "应用系统备案实体类",required = true,dataType = "ApplicationAudit")
    @PreAuthorize("hasAnyAuthority('applicationaudit:edit')")
    @PostMapping("/update")
    @ResponseBody
    public JsonMsg update(ApplicationAudit entity) throws Exception{
        try {
            applicationAuditService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条记录
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "应用系统备案id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('applicationaudit:delete')")
    @DeleteMapping("/delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception {
        try {
            applicationAuditService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //生成年度需审核应用
    @ApiOperation("生成年度需审核应用接口")
    @ApiImplicitParam(name = "annual",value = "备案年度",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('applicationaudit:born')")
    @PostMapping("/born")
    @ResponseBody
    public JsonMsg born(int annual) throws Exception {
        try {
            int rows = applicationAuditService.born(annual);

            return JsonMsg.success().add("rows",rows);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("应用备案配置保存")
    @ApiImplicitParam(name = "entity",value = "备案配置实体类",required = true,dataType = "AppAuditConfigEntity")
    @PreAuthorize("hasAnyAuthority('applicationaudit:set')")
    @PostMapping("/setAppAudit")
    @ResponseBody
    public JsonMsg setAppAudit(AppAuditConfigEntity entity) throws Exception{
        try {
            sysConfigService.setAppAuditConfig(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("备案确认视图")
    @ApiImplicitParam(name = "id",value = "备案应用系统id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('applicationaudit:audit')")
    @GetMapping("/audit")
    public String audit(String id,Model model) throws Exception{
        ApplicationAudit detail = applicationAuditService.detail(id);
        model.addAttribute("model",detail);
        return "areaApplication/applicationAudit/audit";
    }

    @ApiOperation("备案确认保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "备案应用系统id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "status",value = "备案状态",required = true,dataType = "int"),
            @ApiImplicitParam(name = "remark",value = "备注",required = true,dataType = "String")
    })
    @PreAuthorize("hasAnyAuthority('applicationaudit:audit')")
    @PostMapping("/auditSubmit")
    @ResponseBody
    public JsonMsg auditSubmit(String id,int status,String remark) throws Exception{
        try {
            ApplicationAudit entity = applicationAuditService.detail(id);
            if(IsAuditOpen(entity.getAnnual())){
                entity.setId(id);
                entity.setStatus(status);
                entity.setRemark(remark);
                entity.setAuditor(UserUtil.getLoginRealName());
                entity.setAuditTime(new Timestamp(new Date().getTime()));

                applicationAuditService.updateAudit(entity);
                return JsonMsg.success();
            }
            else{
                return JsonMsg.resonpse(410,"备案未开放");
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("导出excel")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "appName",value = "应用名称",required = false,dataType = "String"),
        @ApiImplicitParam(name = "annual",value = "年度",required = false,dataType = "Integer"),
        @ApiImplicitParam(name = "departmentId",value = "部门id",required = false,dataType = "String"),
        @ApiImplicitParam(name = "status",value = "状态",required = false,dataType = "Integer")
    })
    @PreAuthorize("hasAnyAuthority('applicationaudit:excel')")
    @GetMapping("download")
    public void download(String appName, Integer annual,String departmentId,Integer status, HttpServletResponse response) throws Exception {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("appName",appName);
        map.put("annual",annual);
        map.put("departmentId",departmentId);
        map.put("status",status);

        Set<String> excludeColumnFiledNames = new HashSet<String>();

        String name = "应用系统备案";

        List<ApplicationAuditExcel> list = applicationAuditService.listExcel(map);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //传递实体类
        EasyExcel.write(response.getOutputStream(), ApplicationAuditExcel.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet("数据")
                .doWrite(list);
    }

    @ApiOperation("导出word")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "departmentId",value = "部门id",required = true,dataType = "String")
    })
    @PreAuthorize("hasAnyAuthority('applicationaudit:word')")
    @GetMapping("downloadWord")
    public void downloadWord(Integer annual,String departmentId, HttpServletResponse response) throws Exception {


        //查询部门，存在时下载
        Department department = departmentService.detail(departmentId);
        if(department!=null){
            String fileName = department.getDepartmentName() + "-应用系统备案.docx";  //下载文件名
            //查询备案列表
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("annual",annual);
            map.put("departmentId",departmentId);
            map.put("status",1);
            List<ApplicationAuditExcel> list = applicationAuditService.listExcel(map);
            List<ApplicationSelfExcel> list1 = applicationSelfService.listExcel(map);
            //替换到word模板中的数据
            Map<String, String> textMap = new HashMap<String, String>();
            textMap.put("department", department.getDepartmentName());
            List<String[]> rowList = new ArrayList<String[]>();
            int index = 1;
            for (ApplicationAuditExcel item:list) {
                rowList.add(new String[]{Integer.toString(index),item.getAppName(),"网站",item.getAuditor()});
                index++;
            }
            for (ApplicationSelfExcel item: list1) {
                rowList.add(new String[]{Integer.toString(index),item.getAppName(),item.getAppKindName(),item.getLinkMan()});
                index++;
            }

            InputStream in = null;
            XWPFDocument document = null;
            response.reset();
            response.setContentType("application/x-msdownload");
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            OutputStream os = null;
            try {
                response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                //找到项目中模板文件的位置
                in = Thread.currentThread().getContextClassLoader().getResourceAsStream("tp/tp_app_audit.docx");
                //获取docx解析对象
                document = new XWPFDocument(in);
                WordPoiUtil.changeText(document, textMap);
                WordPoiUtil.changeTable(document,textMap,new ArrayList<String[]>());
                WordPoiUtil.changeTable(document,textMap,rowList,1,1,2,16);
                os = response.getOutputStream();
                document.write(os);
                os.write(ostream.toByteArray());os.close();ostream.close();
            } catch (IOException e) { e.printStackTrace(); }
            finally {
                try {
                    if (in != null) { in.close(); }
                    if (document != null) { document.close(); }
                    if (os != null) { os.close(); }
                    if (ostream != null) { ostream.close(); }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //downloadReportFile(fileName,"tp/tp_app_audit.docx", textMap,rowList,1,response);
        }
    }

    /***私有函数***/
    //是否在备案开放期间
    boolean IsAuditOpen(int annual) throws Exception {
        AppAuditConfigEntity appAuditConfig = sysConfigService.getAppAuditConfig();
        boolean open=false;
        if(appAuditConfig.getAppAuditYear() != null && appAuditConfig.getAppAuditStart() != null && appAuditConfig.getAppAuditEnd() !=null){
            if(appAuditConfig.isAppAuditOpen() && annual==appAuditConfig.getAppAuditYear()){
                Date start = appAuditConfig.getAppAuditStart();
                Date end = appAuditConfig.getAppAuditEnd();
                Date now = new Date();
                if(now.after(start) && now.before(end)){
                    open = true;
                }
            }
        }
        return open;
    }


}
