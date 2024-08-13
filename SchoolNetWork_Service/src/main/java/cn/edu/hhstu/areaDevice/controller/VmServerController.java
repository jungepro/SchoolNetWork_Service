package cn.edu.hhstu.areaDevice.controller;

import cn.edu.hhstu.areaApplication.service.IApplicationService;
import cn.edu.hhstu.areaDevice.service.IDeviceService;
import cn.edu.hhstu.areaDevice.service.IVmServerService;
import cn.edu.hhstu.areaStatistics.service.IDeviceLogService;
import cn.edu.hhstu.areaSystem.service.IDepartmentService;
import cn.edu.hhstu.areaSystem.service.IOsService;
import cn.edu.hhstu.entity.Excel.DeviceExcel;
import cn.edu.hhstu.entity.Excel.VmServerExcel;
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

@Api(tags = "虚拟服务器管理")
@Controller
@RequestMapping("vmServer")
public class  VmServerController {

    @Autowired
    private IVmServerService vmServerService;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IOsService osService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IDeviceLogService deviceLogService;
    @Autowired
    private IApplicationService applicationService;

    //列表数据视图
    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String index(Model model) throws Exception {
        List<Os> osList = osService.list();
        model.addAttribute("osList",osList);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("isVm",true);
        map.put("orderByClause","serverName");
        List<Device> deviceList = deviceService.list(map, 1, 100);
        model.addAttribute("deviceList",deviceList);
        return "areaDevice/vmServer/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverName",value = "服务器名称",required = true,dataType = "String"),
            @ApiImplicitParam(name = "ip",value = "ip地址",required = true,dataType = "String"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @PreAuthorize("hasAnyAuthority('vmserver:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list(String serverName, String ip, Integer osId, String deviceId, Integer status, String orderByClause, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception {
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("serverName",serverName);
            map.put("ip",ip);
            map.put("osId",osId);
            map.put("status",status);
            map.put("deviceId",deviceId);
            map.put("orderByClause",orderByClause==null?"d.serverNo desc":orderByClause.replace("'",""));
            List<VmServer> list = vmServerService.list(map, page, rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //详情视图
    @ApiOperation("详情视图")
    @ApiImplicitParam(name = "id",value = "虚拟服务器id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('vmserver:detail')")
    @GetMapping("/detail")
    public String detail(String id, Model model) throws Exception {
        VmServer vmServer = vmServerService.detail(id);
        model.addAttribute("model",vmServer);
        String userName = UserUtil.getLoginUserName();
        model.addAttribute("userName",userName);
        return "areaDevice/vmServer/detail";
    }

    //新增视图
    @ApiOperation("新增视图")
    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority('vmserver:add')")
    public String create(Model model) throws Exception {
        List<Os> osList = osService.list();
        model.addAttribute("osList",osList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("isVm",true);
        map.put("orderByClause","serverName");
        List<Device> deviceList = deviceService.list(map, 1, 100);
        model.addAttribute("deviceList",deviceList);
        return "areaDevice/vmServer/create";
    }

    //新增数据保存接口
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "虚拟服务器实体类",required = true,dataType = "VmServer")
    @PreAuthorize("hasAnyAuthority('vmserver:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(VmServer entity) throws Exception{
        try {
            entity.setStatus(1);
            entity.setId(UUID.randomUUID().toString().replace("-",""));
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());

            vmServerService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "虚拟服务器id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('vmserver:edit')")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        VmServer entity = vmServerService.detail(id);
        model.addAttribute("model",entity);

        List<Os> osList = osService.list();
        model.addAttribute("osList",osList);
        List<Department> departmentList = departmentService.list(null);
        model.addAttribute("departmentList",departmentList);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("isVm",true);
        map.put("orderByClause","serverName");
        List<Device> deviceList = deviceService.list(map, 1, 100);
        model.addAttribute("deviceList",deviceList);

        return "areaDevice/vmServer/edit";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "虚拟服务器实体类",required = true,dataType = "VmServer")
    @PreAuthorize("hasAnyAuthority('vmserver:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(VmServer entity) throws Exception {
        try {
            vmServerService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("生成设备编码接口")
    @GetMapping("/lastDeviceNo")
    @ResponseBody
    public JsonMsg lastDeviceNo(){
        try {
            String deviceNo = vmServerService.lastDeviceNo();
            if(StringUtils.isBlank(deviceNo)){
                deviceNo = "V" + Calendar.getInstance().get(Calendar.YEAR)+ "01";
            }
            else{
                String number = deviceNo.substring(5);
                if(StringUtils.isNotBlank(number) && StringUtils.isNumeric(number)){
                    deviceNo = "V" + Calendar.getInstance().get(Calendar.YEAR) + StringUtils.leftPad(Integer.toString(Integer.valueOf(number)+1),2,'0');
                }
            }
            return JsonMsg.success().add("deviceNo",deviceNo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }


    @ApiOperation("下架视图")
    @PreAuthorize("hasAnyAuthority('vmserver:offline')")
    @GetMapping("/offline")
    public String offline(Model model) {
        String loginRealName = UserUtil.getLoginRealName();
        model.addAttribute("loginRealName",loginRealName);
        return "areaDevice/vmServer/offline";
    }

    @ApiOperation("应用下架保存接口")
    @ApiImplicitParam(name = "entity",value = "日志实体类",required = true,dataType = "DeviceLog")
    @PreAuthorize("hasAnyAuthority('vmserver:offline')")
    @RequestMapping(value = "/offlineSave",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg offlineSave(DeviceLog entity) throws Exception{
        try {
            //先下架应用
            int count = vmServerService.haveApplication(entity.getDeviceId());
            if(count==0){
                if(vmServerService.offline(entity.getDeviceId())>0){
                    /****流转日志****/
                    entity.setCreateTime(new Timestamp(new Date().getTime()));
                    entity.setCreator(UserUtil.getLoginUserName());
                    entity.setLogTypeDesc(DeviceLogUtil.getDesc(entity.getLogType()));
                    VmServer detail = vmServerService.detail(entity.getDeviceId());
                    entity.setDeviceName(detail.getServerName());
                    deviceLogService.insert(entity);
                    /****流转日志****/
                }

                return JsonMsg.success();
            }
            else{
                return JsonMsg.resonpse(201,"请先下架该服务器上部署的应用");
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除数据接口")
    @ApiImplicitParam(name = "id",value = "虚机id",required = true,dataType = "String")
    @DeleteMapping("delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception{
        try {
            String userName = UserUtil.getLoginUserName();
            if (userName.equals("system")){
                int count = vmServerService.haveApplication(id);
                if(count == 0){
                    VmServer detail = vmServerService.detail(id);
                    if(vmServerService.delete(id)>0){
                        /****流转日志****/
                        DeviceLog log = new DeviceLog();
                        log.setLogType(32);
                        log.setLogTypeDesc(DeviceLogUtil.getDesc(32));
                        log.setDeviceId(id);
                        log.setDeviceName(detail.getServerName());
                        log.setOutCreator(UserUtil.getLoginRealName());
                        log.setCreateTime(new Timestamp(new Date().getTime()));
                        log.setCreator(UserUtil.getLoginUserName());
                        deviceLogService.insert(log);
                        /****流转日志****/
                    }

                    return JsonMsg.success();
                }
                else{
                    return JsonMsg.resonpse(201,"请先下架该服务器上部署的应用");
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

    @ApiOperation("导出excel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverName",value = "服务器名称",required = true,dataType = "String"),
            @ApiImplicitParam(name = "position",value = "位置",required = true,dataType = "String"),
            @ApiImplicitParam(name = "manufacturerId",value = "厂商id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "deviceTypeId",value = "设备类型id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "ip",value = "ip地址",required = true,dataType = "String")
    })
    @PreAuthorize("hasAnyAuthority('vmserver:excel')")
    @GetMapping("download")
    public void download(String serverName, Integer osId, String ip, Integer status, HttpServletResponse response) throws Exception {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("serverName",serverName);
        map.put("osId",osId);
        map.put("ip",ip);
        map.put("status",status);

        List<VmServerExcel> list = vmServerService.listExcel(map);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("虚机服务器列表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), VmServerExcel.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("数据")
                .doWrite(list);
    }
}
