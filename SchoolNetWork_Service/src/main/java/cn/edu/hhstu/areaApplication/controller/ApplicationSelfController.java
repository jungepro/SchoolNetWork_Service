package cn.edu.hhstu.areaApplication.controller;

import cn.edu.hhstu.areaApplication.service.IApplicationSelfService;
import cn.edu.hhstu.areaSystem.service.IApplicationKindService;
import cn.edu.hhstu.areaSystem.service.IDepartmentService;
import cn.edu.hhstu.entity.ApplicationSelfEntity;
import cn.edu.hhstu.entity.Excel.ApplicationSelfExcel;
import cn.edu.hhstu.pojo.*;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;


@Api(tags = "自管应用系统管理")
@Controller
@RequestMapping("applicationSelf")
public class  ApplicationSelfController {

    @Autowired
    private IApplicationSelfService applicationSelfService;
    @Autowired
    private IApplicationKindService applicationKindService;
    @Autowired
    IDepartmentService departmentService;

    //列表数据视图
    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String index(Model model) throws Exception {
        List<ApplicationKind> applicationKindList = applicationKindService.list();
        model.addAttribute("applicationKindList",applicationKindList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute(departmentList);

        List<Integer> annualList = new ArrayList<>();
        int year = Integer.parseInt(DateFormatUtils.format(new Date(), "yyyy"));
        for (int i = year; i >=2021; i--) {
            annualList.add(i);
        }
        model.addAttribute("annualList",annualList);

        return "areaApplication/applicationSelf/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('applicationself:query')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appName",value = "应用名称",required = false,dataType = "String"),
            @ApiImplicitParam(name = "applicationKindId",value = "应用类别id",required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "departmentId",value = "部门id",required = false,dataType = "String"),
            @ApiImplicitParam(name = "status",value = "状态",required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @GetMapping("/listPage")
    @ResponseBody
    public JsonMsg listPage(Integer annual, String appName, Integer applicationKindId, String departmentId, Integer status, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception {
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("appName",appName);
            map.put("applicationKindId",applicationKindId);
            map.put("departmentId",departmentId);
            map.put("status",status);
            map.put("annual",annual);
            List<ApplicationSelfEntity> list = applicationSelfService.listPage(map, page, rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //详情视图
    @ApiOperation("详情视图")
    @ApiImplicitParam(name = "id",value = "自建应用系统id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('applicationself:query')")
    @GetMapping("/detail")
    public String detail(String id, Model model) throws Exception {
        ApplicationSelfEntity entity = applicationSelfService.detail(id);
        model.addAttribute("model",entity);
        String userName = UserUtil.getLoginUserName();
        model.addAttribute("userName",userName);
        return "areaApplication/applicationSelf/detail";
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('applicationself:add')")
    @GetMapping("/create")
    public String create(String deviceId, Model model) throws Exception {
        List<ApplicationKind> applicationKindList = applicationKindService.list();
        model.addAttribute("applicationKindList",applicationKindList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);
        return "areaApplication/applicationSelf/create";
    }

    //新增数据保存接口
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "应用系统实体类",required = true,dataType = "ApplicationSelf")
    @PreAuthorize("hasAnyAuthority('applicationself:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(ApplicationSelf entity) throws Exception{
        try {
            entity.setStatus(1);
            entity.setId(UUID.randomUUID().toString().replace("-",""));
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            applicationSelfService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("应用系统编辑视图")
    @ApiImplicitParam(name = "id",value = "应用系统id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('applicationself:edit')")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        ApplicationSelfEntity entity = applicationSelfService.detail(id);
        model.addAttribute("model",entity);
        List<ApplicationKind> applicationKindList = applicationKindService.list();
        model.addAttribute("applicationKindList",applicationKindList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);
        return "areaApplication/applicationSelf/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "应用系统实体类",required = true,dataType = "ApplicationSelf")
    @PreAuthorize("hasAnyAuthority('applicationself:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(ApplicationSelf entity) throws Exception {
        try {
            applicationSelfService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除数据接口")
    @ApiImplicitParam(name = "id",value = "应用系统id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('applicationself:delete')")
    @DeleteMapping("/delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception{
        try {
            String userName = UserUtil.getLoginUserName();
            if (userName.equals("system")){
                int count = applicationSelfService.delete(id);
                return  JsonMsg.success();
            }
            else {
                return JsonMsg.resonpse(201,"仅system用户具有删除权限");
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("导出excel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appName",value = "应用名称",required = false,dataType = "String"),
            @ApiImplicitParam(name = "applicationKindId",value = "应用类别id",required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "departmentId",value = "部门id",required = false,dataType = "String"),
            @ApiImplicitParam(name = "status",value = "状态",required = false,dataType = "Integer"),
    })
    @PreAuthorize("hasAnyAuthority('applicationself:excel')")
    @GetMapping("download")
    public void download(String appName, Integer applicationKindId, String departmentId, Integer status, HttpServletResponse response) throws Exception {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("appName",appName);
        map.put("applicationKindId",applicationKindId);
        map.put("departmentId",departmentId);
        map.put("status",status);

        Set<String> excludeColumnFiledNames = new HashSet<String>();

        String name = "自营应用系统";

        List<ApplicationSelfExcel> list = applicationSelfService.listExcel(map);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //传递实体类
        EasyExcel.write(response.getOutputStream(), ApplicationSelfExcel.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet("数据")
                .doWrite(list);
    }
}
