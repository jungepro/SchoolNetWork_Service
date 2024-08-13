package cn.edu.hhstu.areaStatistics.controller;

import cn.edu.hhstu.areaStatistics.service.IDeviceLogService;
import cn.edu.hhstu.pojo.ApplicationType;
import cn.edu.hhstu.pojo.DeviceLog;
import cn.edu.hhstu.utils.DeviceLogUtil;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;



@Api(tags = "设备/应用日志管理")
@Controller
@RequestMapping("deviceLog")
public class  DeviceLogController {

    @Autowired
    private IDeviceLogService deviceLogService;

    @ApiOperation("列表视图")
    @GetMapping("/logList")
    public String logList(String deviceId, Model model) throws Exception{
        List<DeviceLog> list = deviceLogService.list(deviceId);
        model.addAttribute("logList",list);
        return "areaStatistics/deviceLog/logList";
    }

    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "应用类型id",required = true,dataType = "Integer")
//    @PreAuthorize("hasAnyAuthority('devicelog:edit')")
    @GetMapping("/edit")
    public String edit(Integer id, Model model) throws Exception {
        DeviceLog entity = deviceLogService.detail(id);
        model.addAttribute("model",entity);
        return "areaStatistics/deviceLog/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "应用类型实体类",required = true,dataType = "DeviceLogUtil")
//    @PreAuthorize("hasAnyAuthority('devicelog:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(DeviceLog entity) throws Exception {
        try {
            deviceLogService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "应用类型id",required = true,dataType = "Integer")
//    @PreAuthorize("hasAnyAuthority('devicelog:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(Integer id) throws Exception {
        try {
            deviceLogService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
