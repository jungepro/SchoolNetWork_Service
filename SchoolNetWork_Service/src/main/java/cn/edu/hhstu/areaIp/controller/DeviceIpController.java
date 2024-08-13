package cn.edu.hhstu.areaIp.controller;

import cn.edu.hhstu.areaIp.service.IDeviceIpService;
import cn.edu.hhstu.areaSystem.service.IDeviceTypeService;
import cn.edu.hhstu.pojo.DeviceIp;
import cn.edu.hhstu.pojo.DeviceType;
import cn.edu.hhstu.utils.JsonMsg;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "设备已配置IP地址管理")
@Controller
@RequestMapping("deviceIp")
public class  DeviceIpController {

    @Autowired
    private IDeviceIpService deviceIpService;
    @Autowired
    private IDeviceTypeService deviceTypeService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index(Model model) throws Exception{
        List<DeviceType> list = deviceTypeService.list();
        model.addAttribute("deviceTypeList",list);
        return "areaIp/deviceIp/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip",value = "IP地址",required = true,dataType = "String"),
            @ApiImplicitParam(name = "deviceTypeId",value = "设备类型",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @PreAuthorize("hasAnyAuthority('deviceip:query')")
    @GetMapping("/listPage")
    @ResponseBody
    public JsonMsg listPage(String ip, Integer deviceTypeId,Integer openType, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            List<DeviceIp> list = deviceIpService.listPage(ip,deviceTypeId,openType,page,rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除配置设备
    @ApiOperation("删除设备配置ip接口")
    @ApiImplicitParam(name = "ipAddressId",value = "配置ip地址id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('deviceip:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(int ipAddressId) throws Exception {
        try {
            deviceIpService.deleteDeviceIp(ipAddressId);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条设备配置ip数据接口")
    @ApiImplicitParam(name = "ids",value = "IP地址id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('deviceip:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            deviceIpService.deleteDeviceIpByIds(intArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
