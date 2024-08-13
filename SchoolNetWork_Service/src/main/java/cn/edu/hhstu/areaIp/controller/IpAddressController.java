package cn.edu.hhstu.areaIp.controller;

import cn.edu.hhstu.areaIp.service.IDnsService;
import cn.edu.hhstu.areaIp.service.IIpAddressService;
import cn.edu.hhstu.areaIp.service.IIpDomainService;
import cn.edu.hhstu.pojo.*;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.RegExpUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "IP地址管理")
@Controller
@RequestMapping("ipAddress")
public class  IpAddressController {

    @Autowired
    private IIpAddressService ipAddressService;
    @Autowired
    private IIpDomainService ipDomainService;
    @Autowired
    private IDnsService dnsService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index(Model model) throws Exception{
        List<IpDomain> list = ipDomainService.list();
        model.addAttribute("ipDomainList",list);
        return "areaIp/ipAddress/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip",value = "IP地址",required = true,dataType = "String"),
            @ApiImplicitParam(name = "domainId",value = "IP范畴id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @PreAuthorize("hasAnyAuthority('ipaddress:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list(String ip,Integer domainId, Integer openType, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            List<IpAddress> list = ipAddressService.list(ip,domainId,openType,page,rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("IP地址生成接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "poolId",value = "IP地址池id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "startIp",value = "起始IP",required = true,dataType = "String"),
            @ApiImplicitParam(name = "ipNum",value = "数量",required = true,dataType = "Integer")
    })
    @PreAuthorize("hasAnyAuthority('ippool:born')")
    @PostMapping("/born")
    @ResponseBody
    public JsonMsg born(Integer poolId,String startIp,Integer ipNum){
        try {
            if(StringUtils.isBlank(startIp) || poolId == null || ipNum == null)
            {
                return JsonMsg.fail();
            }
            else if(!RegExpUtil.isIP((startIp)))
            {
                return JsonMsg.fail();
            }

            IpAddress ipAddress = new IpAddress();
            ipAddress.setPoolId(poolId);
            ipAddress.setCreateTime(new Timestamp(new Date().getTime()));
            ipAddress.setCreator(UserUtil.getLoginUserName());

            String ip;
            String[] ips = startIp.split("\\.");
            int insert = 0,update = 0;
            int tmp = Integer.parseInt(ips[3]);
            for (int i = 0; i < ipNum; i++)
            {
                if (tmp<255)
                {
                    ip = ips[0] + "." + ips[1] + "." + ips[2] + "." +  tmp;
                    ipAddress.setIp(ip);
                    if (ipAddressService.existIp(ip)==0)
                    {
                        //添加
                        ipAddressService.insert(ipAddress);
                        insert++;
                    }
                    else{
                        update++;
                    }
                    tmp++;
                }
                else
                    break;
            }
            Map map = new HashMap();
            map.put("insert",insert);
            map.put("update",update);
            return JsonMsg.success().add("msg",map);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ipaddress:edit')")
    @GetMapping("/edit")
    public String edit(int id, Model model) throws Exception {
        IpAddress entity = ipAddressService.detail(id);
        model.addAttribute("model",entity);
        return "areaIp/ipAddress/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "IP地址实体类",required = true,dataType = "IpAddress")
    @PreAuthorize("hasAnyAuthority('ipaddress:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(IpAddress entity) throws Exception {
        try {
            ipAddressService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }


//    @ApiOperation("备注保存接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "IP地址id",required = true,dataType = "Integer"),
//            @ApiImplicitParam(name = "msg",value = "备注",required = true,dataType = "String")
//    })
//    @PreAuthorize("hasAnyAuthority('ipaddress:remark')")
//    @PostMapping("/remark")
//    @ResponseBody
//    public JsonMsg remark(Integer id,String msg)
//    {
//        try {
//            ipAddressService.remark(id,msg);
//            return JsonMsg.success();
//        }
//        catch (Exception ex){
//            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
//        }
//    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "IP地址id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ipaddress:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(int id) throws Exception {
        try {
            ipAddressService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "IP地址id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('ipaddress:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            ipAddressService.deleteByIds(intArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    /*************************配置设备ip**************************/
    @ApiOperation("设备已配置ip列表视图")
    @GetMapping("deviceIp")
    public  String deviceIp(Model model) throws Exception{
        List<IpDomain> list = ipDomainService.list();
        model.addAttribute("ipDomainList",list);
        return "areaIp/ipAddress/deviceIp";
    }
    @ApiOperation("设备已配置ip列表数据接口")
    @ApiImplicitParam(name = "domainId",value = "IP范畴id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('deviceip:query')")
    @GetMapping("deviceIps")
    @ResponseBody
    public JsonMsg deviceIps(Integer domainId, String ip,Integer openType, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            List<IpAddress> list = ipAddressService.deviceIps(domainId,ip,openType,page,rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("配置设备ip视图")
    @PreAuthorize("hasAnyAuthority('deviceserver:ip','devicestorage:ip','devicewhole:ip','deviceswitch:ip','vmserver:ip')")
    @GetMapping("selectDeviceIp")
    public String selectDeviceIp() throws Exception{
        return "areaIp/ipAddress/selectDeviceIp";
    }

    @ApiOperation("指定设备已配置ip列表数据接口")
    @ApiImplicitParam(name = "deviceId",value = "设备id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('deviceserver:ip','devicestorage:ip','devicewhole:ip','deviceswitch:ip','vmserver:ip')")
    @GetMapping("listDeviceIp")
    @ResponseBody
    public JsonMsg listDeviceIp(String deviceId) throws Exception{
        try {
            List<IpAddress> list = ipAddressService.listDeviceIp(deviceId);
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //设备未使用ip地址
    @ApiOperation("获取设备未使用ip地址接口")
    @ApiImplicitParam(name = "ip",value = "ip地址",required = true,dataType = "String")
    @GetMapping("listUnUse")
    @ResponseBody
    public JsonMsg listUnUse(String ip) throws Exception{
        try {
            List<IpAddress> list = ipAddressService.listUnUse(ip,10);
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }


    //配置设备ip
    @ApiOperation("保存设备配置IP接口")
    @ApiImplicitParam(name = "entity",value = "设备ip实体类",required = true,dataType = "DeviceIp")
    @PreAuthorize("hasAnyAuthority('deviceserver:ip','devicestorage:ip','devicewhole:ip','deviceswitch:ip','vmserver:ip')")
    @PostMapping("insertDeviceIp")
    @ResponseBody
    public JsonMsg insertDeviceIp(DeviceIp entity) throws Exception {
        try {
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            ipAddressService.insertDeviceIp(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除配置设备
    @ApiOperation("删除设备配置ip接口")
    @ApiImplicitParam(name = "ipAddressId",value = "配置ip地址id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('deviceip:delete','deviceserver:ip','devicestorage:ip','devicewhole:ip','deviceswitch:ip','vmserver:ip')")
    @DeleteMapping(value = "/deleteDeviceIp")
    @ResponseBody
    public JsonMsg deleteDeviceIp(int ipAddressId) throws Exception {
        try {
            ipAddressService.deleteDeviceIp(ipAddressId);
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
    @DeleteMapping(value = "/deleteDeviceIpByIds")
    @ResponseBody
    public JsonMsg deleteDeviceIpByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            ipAddressService.deleteDeviceIpByIds(intArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    /*******************域名和端口******************/
    @ApiOperation("配置设备ip视图")
    @ApiImplicitParam(name = "id",value = "IP地址id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ipaddress:dns')")
    @GetMapping("selectDns")
    public String selectDns(int id,Model model) throws Exception{
        List<Dns> dnsList = dnsService.listUnuseDns(id);
        model.addAttribute("dnsList",dnsList);
        model.addAttribute("ipAddressId",id);
        return "areaIp/ipAddress/selectDns";
    }

    @ApiOperation("ip对应域名列表数据接口")
    @ApiImplicitParam(name = "ipAddressId",value = "ip地址id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ipaddress:dns')")
    @GetMapping("/listIpDns")
    @ResponseBody
    public JsonMsg listIpDns(int ipAddressId) throws Exception{
        try {
            List<IpDns> list = dnsService.listIpDns(ipAddressId);
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("ip对应域名保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dnsId",value = "域名id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "ipAddressId",value = "ip地址id",required = true,dataType = "Integer")
    })
    @PreAuthorize("hasAnyAuthority('ipaddress:dns')")
    @PostMapping("/insertIpDns")
    @ResponseBody
    public JsonMsg insertIpDns(Integer dnsId,Integer ipAddressId) throws Exception{
        try {
            if(ipAddressId==null || dnsId==null){
                return JsonMsg.resonpse(201,"ip地址或域名不能为空");
            }
            else{
                dnsService.insertIpDns(dnsId,ipAddressId);
                return JsonMsg.success();
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("ip对应域名删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dnsId",value = "域名id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "ipAddressId",value = "ip地址id",required = true,dataType = "Integer")
    })
    @PreAuthorize("hasAnyAuthority('ipaddress:dns')")
    @DeleteMapping("/deleteIpDns")
    @ResponseBody
    public JsonMsg deleteIpDns(Integer dnsId,Integer ipAddressId) throws Exception{
        try {
            if(ipAddressId==null || dnsId==null){
                return JsonMsg.resonpse(201,"ip地址或域名不能为空");
            }
            else{
                dnsService.deleteIpDns(dnsId,ipAddressId);
                return JsonMsg.success();
            }
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
