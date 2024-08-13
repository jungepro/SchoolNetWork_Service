package cn.edu.hhstu.areaDocument.controller;

import cn.edu.hhstu.areaDevice.service.IDeviceService;
import cn.edu.hhstu.areaDevice.service.IVmServerService;
import cn.edu.hhstu.areaDocument.service.IOperationLogService;
import cn.edu.hhstu.areaDocument.service.IOperationLogTypeService;
import cn.edu.hhstu.pojo.Device;
import cn.edu.hhstu.pojo.OperationLog;
import cn.edu.hhstu.pojo.OperationLogType;
import cn.edu.hhstu.pojo.VmServer;
import cn.edu.hhstu.utils.DateUtil;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
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

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;



@Api(tags = "设备操作日志管理")
@Controller
@RequestMapping("operationLog")
public class  OperationLogController {

    @Autowired
    private IOperationLogService operationLogService;
    @Autowired
    private IOperationLogTypeService operationLogTypeService;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IVmServerService vmServerService;

    @ApiOperation("设备操作列表视图")
    @GetMapping("/index")
    public String index(Model model) throws Exception{
        List<OperationLogType> operationLogTypeList = operationLogTypeService.list();
        model.addAttribute("operationLogTypeList",operationLogTypeList);
        return "areaDocument/operationLog/index";
    }

    @ApiOperation("列表数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "operator",value = "操作人",required = true,dataType = "String"),
            @ApiImplicitParam(name = "deviceName",value = "设备名称",required = true,dataType = "String"),
            @ApiImplicitParam(name = "operationLogTypeId",value = "日志类型id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })

    @GetMapping("listPage")
    @PreAuthorize("hasAnyAuthority('operationlog:query')")
    @ResponseBody
    public JsonMsg listPage(String operator, String deviceName, Integer operationLogTypeId, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            List<OperationLog> list = operationLogService.listPage(operator, deviceName, operationLogTypeId, page, rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('operationlog:add')")
    @GetMapping("create")
    public String create(String deviceId, Integer deviceTypeId, Model model) throws Exception{
        List<OperationLogType> operationLogTypeList = operationLogTypeService.list();
        model.addAttribute("operationLogTypeList",operationLogTypeList);
        if(deviceTypeId != null){
            if(deviceTypeId == 11){
                VmServer vmServer = vmServerService.detail(deviceId);
                model.addAttribute("serverName",vmServer.getServerName());
            }
            else{
                Device device = deviceService.detail(deviceId);
                model.addAttribute("serverName",device.getServerName());
            }
        }
        else{
            model.addAttribute("serverName","");
        }
        model.addAttribute("operator",UserUtil.getLoginRealName());
        model.addAttribute("operationDate", DateUtil.date2String(new Date(),"yyyy-MM-dd"));
        return "areaDocument/operationLog/create";
    }

    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "操作日志实体类",required = true,dataType = "OperationLog")
    @PreAuthorize("hasAnyAuthority('operationlog:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(OperationLog entity) throws Exception{
        try {
            entity.setId(UUID.randomUUID().toString().replace("-",""));
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            operationLogService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "操作日志id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('operationlog:edit')")
    @GetMapping("/edit")
    public String edit(String id,Model model) throws Exception{
        List<OperationLogType> operationLogTypeList = operationLogTypeService.list();
        model.addAttribute("operationLogTypeList",operationLogTypeList);
        OperationLog detail = operationLogService.detail(id);
        model.addAttribute("model",detail);
        return "areaDocument/operationLog/update";
    }

    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "操作日志实体类",required = true,dataType = "OperationLog")
    @PreAuthorize("hasAnyAuthority('operationlog:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(OperationLog entity) throws Exception{
        try {
            operationLogService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "操作日志id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('operationlog:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception{
        try {
            operationLogService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("设备详情操作日志列表视图")
    @GetMapping("/list")
    public String list() throws Exception{

        return "areaDocument/operationLog/list";
    }

    @ApiOperation("设备详情操作日志列表数据接口")
    @ApiImplicitParam(name = "deviceId",value = "设备id",required = true,dataType = "String")
    @GetMapping("/getList")
    @ResponseBody
    public JsonMsg listByDevice(String deviceId) throws Exception{
        try {
            if(StringUtils.isBlank(deviceId)){
                return JsonMsg.resonpse(400,"设备id不能为空");
            }
            else {
                List<OperationLog> list = operationLogService.listByDevice(deviceId);
                return JsonMsg.success().add("list",list);
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
