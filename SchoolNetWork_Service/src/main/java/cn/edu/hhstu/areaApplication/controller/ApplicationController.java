package cn.edu.hhstu.areaApplication.controller;

import cn.edu.hhstu.areaApplication.service.IApplicationService;
import cn.edu.hhstu.areaStatistics.service.IDeviceLogService;
import cn.edu.hhstu.areaSystem.service.IApplicationTypeService;
import cn.edu.hhstu.areaSystem.service.IDepartmentService;
import cn.edu.hhstu.entity.DeviceEntity;
import cn.edu.hhstu.entity.Excel.ApplicationExcel;
import cn.edu.hhstu.entity.Excel.DeviceExcel;
import cn.edu.hhstu.pojo.*;
import cn.edu.hhstu.utils.DeviceLogUtil;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

@Api(tags = "应用系统管理")
@Controller
@RequestMapping("application")
public class  ApplicationController {

    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IApplicationTypeService applicationTypeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IDeviceLogService deviceLogService;

    //列表数据视图
    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String index(Model model) throws Exception {
        List<ApplicationType> applicationTypeList = applicationTypeService.list();
        model.addAttribute("applicationTypeList",applicationTypeList);
        return "areaApplication/application/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('application:query')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appName",value = "应用名称",required = false,dataType = "String"),
            @ApiImplicitParam(name = "applicationTypeId",value = "应用类型id",required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "record",value = "是否备案",required = false,dataType = "Boolean"),
            @ApiImplicitParam(name = "status",value = "状态",required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @GetMapping("/listPage")
    @ResponseBody
    public JsonMsg listPage(String appName,Integer applicationTypeId, Boolean record, Integer status, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception {
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("appName",appName);
            map.put("applicationTypeId",applicationTypeId);
            map.put("record",record);
            map.put("status",status);
            map.put("pid",0);
            List<Application> list = applicationService.listPage(map, page, rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("设备详情应用列表视图")
    @GetMapping("/list")
    public String list() throws Exception{

        return "areaApplication/application/list";
    }

    @ApiOperation("设备详情应用列表数据接口")
    @ApiImplicitParam(name = "deviceId",value = "设备id",required = true,dataType = "String")
    @GetMapping("/getList")
    @ResponseBody
    public JsonMsg getList(String deviceId) throws Exception{
        try {
            if(StringUtils.isBlank(deviceId)){
                return JsonMsg.resonpse(400,"设备id不能为空");
            }
            else{
                HashMap<String,Object> map = new HashMap<String,Object>();
                map.put("deviceId",deviceId);
                List<Application> list = applicationService.listForDevice(map);
                return JsonMsg.success().add("list",list);
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }


    //详情视图
    @ApiOperation("详情视图")
    @ApiImplicitParam(name = "id",value = "应用系统id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('application:detail')")
    @GetMapping("/detail")
    public String detail(String id, Model model) throws Exception {
        Application entity = applicationService.detail(id);
        model.addAttribute("model",entity);
        String userName = UserUtil.getLoginUserName();
        model.addAttribute("userName",userName);
        return "areaApplication/application/detail";
    }

    //子应用列表
    @ApiOperation("详情视图")
    @ApiImplicitParam(name = "pid",value = "应用系统父id",required = true,dataType = "String")
    @GetMapping("/listChildren")
    @ResponseBody
    public JsonMsg listChildren(String pid) throws Exception {
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("pid",pid);
            List<Application> list = applicationService.listChildren(map);
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @GetMapping("/create")
    public String create(String deviceId, Model model) throws Exception {
        List<ApplicationType> applicationTypeList = applicationTypeService.list();
        model.addAttribute("applicationTypeList",applicationTypeList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);
        DeviceEntity deviceEntity = applicationService.deviceDetail(deviceId);
        model.addAttribute("deviceEntity",deviceEntity);

        return "areaApplication/application/create";
    }

    //新增子应用视图
    @ApiOperation("应用子系统新增视图")
    @GetMapping("/createChild")
    public String createChild(Model model) throws Exception {
        List<ApplicationType> applicationTypeList = applicationTypeService.list();
        model.addAttribute("applicationTypeList",applicationTypeList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);
        return "areaApplication/application/createChild";
    }

    //新增数据保存接口
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "应用系统实体类",required = true,dataType = "Application")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Application entity) throws Exception{
        try {
            entity.setStatus(1);
            entity.setId(UUID.randomUUID().toString().replace("-",""));
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            if(StringUtils.isBlank(entity.getPid())){
                entity.setPid("0");
            }
            applicationService.insert(entity);

            /****流转日志****/
            DeviceLog log = new DeviceLog();
            log.setLogType(10);
            log.setLogTypeDesc(DeviceLogUtil.getDesc(10));
            log.setDeviceId(entity.getId());
            log.setDeviceName(entity.getAppName());
            log.setOutCreator(UserUtil.getLoginRealName());
            log.setCreateTime(new Timestamp(new Date().getTime()));
            log.setCreator(UserUtil.getLoginUserName());
            deviceLogService.insert(log);
            /****流转日志****/

            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑子应用视图
    @ApiOperation("应用子系统编辑视图")
    @ApiImplicitParam(name = "id",value = "应用系统id",required = true,dataType = "String")
    @GetMapping("/editChild")
    public String editChild(String id, Model model) throws Exception {
        Application entity = applicationService.detail(id);
        model.addAttribute("model",entity);
        List<ApplicationType> applicationTypeList = applicationTypeService.list();
        model.addAttribute("applicationTypeList",applicationTypeList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);

        return "areaApplication/application/modifyChild";
    }

    //编辑视图
    @ApiOperation("应用系统编辑视图")
    @ApiImplicitParam(name = "id",value = "应用系统id",required = true,dataType = "String")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        Application entity = applicationService.detail(id);
        model.addAttribute("model",entity);

        List<ApplicationType> applicationTypeList = applicationTypeService.list();
        model.addAttribute("applicationTypeList",applicationTypeList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);
        DeviceEntity deviceEntity = applicationService.deviceDetail(entity.getDeviceId());
        model.addAttribute("deviceEntity",deviceEntity);

        return "areaApplication/application/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "应用系统实体类",required = true,dataType = "Application")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(Application entity) throws Exception {
        try {
            applicationService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("变更设备视图")
    @GetMapping("alterDevice")
    public String alterDevice(){
        return "areaApplication/application/alterDevice";
    }

    @ApiOperation("获取变更设备列表数据接口")
    @ApiImplicitParam(name = "serverName",value = "服务器名称",required = true,dataType = "String")
    @GetMapping("listDevice")
    @ResponseBody
    public  JsonMsg listDevice(String serverName) throws Exception{
        try {
            List<DeviceEntity> list = applicationService.deviceList(serverName, null);
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("变更设备保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "应用系统id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "deviceId",value = "设备id",required = true,dataType = "String"),
    })
    @PostMapping("alterDevice")
    @ResponseBody
    public JsonMsg alterDevice(String id,String deviceId) throws Exception{
        try {
            Application detail = applicationService.detail(id);
            int count = applicationService.alterDevice(id, deviceId);

            if(count>0) {
                /****流转日志****/
                DeviceLog log = new DeviceLog();
                log.setLogType(13);
                log.setLogTypeDesc(DeviceLogUtil.getDesc(13));
                log.setDeviceId(id);
                log.setDeviceName(detail.getAppName());
                log.setOutCreator(UserUtil.getLoginRealName());
                log.setRemark("原设备id：" + detail.getDeviceId());
                log.setCreateTime(new Timestamp(new Date().getTime()));
                log.setCreator(UserUtil.getLoginUserName());
                deviceLogService.insert(log);
                /****流转日志****/
            }

            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("应用流转日志视图")
    @ApiImplicitParam(name = "entity",value = "应用id",required = true,dataType = "id")
    @GetMapping("/flowLog")
    public String flowLog(String id, Model model) throws Exception{
        List<DeviceLog> deviceLogs = deviceLogService.list(id);
        model.addAttribute("deviceLogs",deviceLogs);
        return "areaApplication/application/flowLog";
    }

    @ApiOperation("应用下架视图")
    @PreAuthorize("hasAnyAuthority('application:down')")
    @GetMapping("/offline")
    public String offline(Model model) {
        String loginRealName = UserUtil.getLoginRealName();
        model.addAttribute("loginRealName",loginRealName);
        return "areaApplication/application/offline";
    }

    @ApiOperation("应用下架保存接口")
    @ApiImplicitParam(name = "entity",value = "日志实体类",required = true,dataType = "DeviceLog")
    @PreAuthorize("hasAnyAuthority('application:down')")
    @RequestMapping(value = "/offlineSave",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg offlineSave(DeviceLog entity) throws Exception{
        try {
            //父子应用全部下架
            int count = applicationService.offline(entity.getDeviceId());
            if(count>0){
                entity.setCreateTime(new Timestamp(new Date().getTime()));
                entity.setCreator(UserUtil.getLoginUserName());
                entity.setLogTypeDesc(DeviceLogUtil.getDesc(entity.getLogType()));
                Application application = applicationService.detail(entity.getDeviceId());
                entity.setDeviceName(application.getAppName());

                deviceLogService.insert(entity);
            }

            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除数据接口")
    @ApiImplicitParam(name = "id",value = "应用系统id",required = true,dataType = "String")
    @DeleteMapping("delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception{
        try {
            String userName = UserUtil.getLoginUserName();
            if (userName.equals("system")){
                Application detail = applicationService.detail(id);
                if(detail.getStatus() == 0){
                    int count = applicationService.delete(id);

                    if(count>0) {
                        /****流转日志****/
                        DeviceLog log = new DeviceLog();
                        log.setLogType(14);
                        log.setLogTypeDesc(DeviceLogUtil.getDesc(14));
                        log.setDeviceId(id);
                        log.setDeviceName(detail.getAppName());
                        log.setOutCreator(UserUtil.getLoginRealName());
                        log.setCreateTime(new Timestamp(new Date().getTime()));
                        log.setCreator(UserUtil.getLoginUserName());
                        deviceLogService.insert(log);
                        /****流转日志****/
                    }
                    return JsonMsg.success();
                }
                else{
                    return JsonMsg.resonpse(201,"不能删除非下架状态应用");
                }
            }
            else {
                return JsonMsg.resonpse(201,"仅system用户具有删除权限");
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }


    @ApiOperation("删除子系统数据接口")
    @ApiImplicitParam(name = "id",value = "子应用系统id",required = true,dataType = "String")
    @DeleteMapping("deleteChild")
    @ResponseBody
    public JsonMsg deleteChild(String id) throws Exception{
        try {
            String userName = UserUtil.getLoginUserName();
            if (userName.equals("system")){
//                Application detail = applicationService.detail(id);
                int count = applicationService.delete(id);

//                if(count>0) {
//                    /****流转日志****/
//                    DeviceLog log = new DeviceLog();
//                    log.setLogType(16);
//                    log.setLogTypeDesc(DeviceLogUtil.getDesc(16));
//                    log.setDeviceId(detail.getPid());
//                    log.setDeviceName(detail.getAppName());
//                    log.setOutCreator(UserUtil.getLoginRealName());
//                    log.setCreateTime(new Timestamp(new Date().getTime()));
//                    log.setCreator(UserUtil.getLoginUserName());
//                    deviceLogService.insert(log);
//                    /****流转日志****/
//                }
                return JsonMsg.success();
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
            @ApiImplicitParam(name = "applicationTypeId",value = "应用类型id",required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "record",value = "是否备案",required = false,dataType = "Boolean"),
            @ApiImplicitParam(name = "status",value = "状态",required = false,dataType = "Integer")
    })
    @PreAuthorize("hasAnyAuthority('application:excel')")
    @GetMapping("download")
    public void download(String appName, Integer applicationTypeId, Boolean record, Integer status, HttpServletResponse response) throws Exception {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("appName",appName);
        map.put("applicationTypeId",applicationTypeId);
        map.put("record",record);
        map.put("status",status);
        map.put("pid","0");

        Set<String> excludeColumnFiledNames = new HashSet<String>();

        String name = "应用系统";

        List<ApplicationExcel> list = applicationService.listExcel(map);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        //传递实体类
        EasyExcel.write(response.getOutputStream(), ApplicationExcel.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet("数据")
                .doWrite(list);
    }
}
