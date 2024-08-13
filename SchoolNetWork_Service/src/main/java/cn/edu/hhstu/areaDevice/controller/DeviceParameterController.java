package cn.edu.hhstu.areaDevice.controller;

import cn.edu.hhstu.areaDevice.service.IDeviceParameterService;
import cn.edu.hhstu.areaSystem.service.IDefaultParameterService;
import cn.edu.hhstu.pojo.DefaultParameter;
import cn.edu.hhstu.pojo.DeviceParameter;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
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


@Api(tags = "设备参数管理")
@Controller
@RequestMapping("deviceParameter")
public class  DeviceParameterController {

    @Autowired
    private IDeviceParameterService deviceParameterService;
    @Autowired
    private IDefaultParameterService defaultParameterService;

    @ApiOperation("列表视图")
    @PreAuthorize("hasAnyAuthority('deviceserver:param','devicestorage:param','devicewhole:param','deviceswitch:param','vmserver:param')")
    @GetMapping("/index")
    public String index(Model model) throws Exception{
        return "areaDevice/deviceParameter/index";
    }

    @ApiOperation("列表数据接口")
    @ApiImplicitParam(name = "deviceId",value = "设备id",required = true,dataType = "String")
    @GetMapping("list")
    @ResponseBody
    public JsonMsg list(String deviceId) throws Exception{
        try {
            List<DeviceParameter> deviceParameterList = deviceParameterService.list(deviceId);
            return JsonMsg.success().add("list",deviceParameterList);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
    @ApiOperation("新增视图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceTypeId",value = "设备类型id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "deviceId",value = "设备id",required = true,dataType = "String")
    })
    @GetMapping("/create")
    public String create(Integer deviceTypeId,String deviceId, Model model) throws Exception{
        List<DefaultParameter> list = defaultParameterService.unuseList(deviceTypeId,deviceId);
        model.addAttribute("paramList",list);
        return "areaDevice/deviceParameter/create";
    }

    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "设备参数实体类",required = true,dataType = "DeviceParameter")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(DeviceParameter entity) throws Exception{
        try {
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            String[] strs = StringUtils.split(entity.getParamName(),",,");
            entity.setOrderNo(Integer.parseInt(strs[0]));
            entity.setParamName(strs[1]);
            if(strs.length>2){
                entity.setUnitName(strs[2]);
            }

            deviceParameterService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "设备参数id",required = true,dataType = "Integer")
    @GetMapping("/edit")
    public String edit(int id, Model model) throws Exception{
        DeviceParameter detail = deviceParameterService.detail(id);
        model.addAttribute("model",detail);
        return "areaDevice/deviceParameter/update";
    }

    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "设备参数实体类",required = true,dataType = "DeviceParameter")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(DeviceParameter entity) throws Exception{
        try {
            deviceParameterService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除单条数据视图")
    @ApiImplicitParam(name = "id",value = "设备参数id",required = true,dataType = "Integer")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(Integer id) throws Exception {
        try {
            deviceParameterService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
