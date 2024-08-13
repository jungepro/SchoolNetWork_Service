package cn.edu.hhstu.areaDevice.controller;

import cn.edu.hhstu.areaDevice.service.IDeviceBatchService;
import cn.edu.hhstu.areaDevice.service.IDeviceService;
import cn.edu.hhstu.areaIp.service.IIpAddressService;
import cn.edu.hhstu.areaSystem.service.IDepartmentService;
import cn.edu.hhstu.areaSystem.service.IManufacturerService;
import cn.edu.hhstu.areaSystem.service.IOsService;
import cn.edu.hhstu.areaSystem.service.IRoomService;
import cn.edu.hhstu.entity.Excel.DeviceBatchExcel;
import cn.edu.hhstu.entity.Excel.DeviceExcel;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

@Api(tags = "物理设备管理")
@Controller
@RequestMapping("device")
public class  DeviceController {

    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IDeviceBatchService deviceBatchService;
    @Autowired
    private IOsService osService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IManufacturerService manufacturerService;
    @Autowired
    private IIpAddressService ipAddressService;

    @ApiOperation("服务器列表视图")
    @GetMapping("/serverIndex")
    public String serverIndex(Model model) throws Exception{
        List<Manufacturer> manufacturers = manufacturerService.list();
        model.addAttribute("manufacturerList",manufacturers);
        return "areaDevice/device/serverIndex";
    }
    @ApiOperation("存储阵列列表视图")
    @GetMapping("/storageIndex")
    public String storageIndex(Model model) throws Exception{
        List<Manufacturer> manufacturers = manufacturerService.list();
        model.addAttribute("manufacturerList",manufacturers);
        return "areaDevice/device/storageIndex";
    }
    @ApiOperation("一体化设备列表视图")
    @GetMapping("/wholeIndex")
    public String deviceIndex(Model model) throws Exception{
        List<Manufacturer> manufacturers = manufacturerService.list();
        model.addAttribute("manufacturerList",manufacturers);
        return "areaDevice/device/wholeIndex";
    }
    @ApiOperation("交换机列表视图")
    @GetMapping("/switchIndex")
    public String switchIndex(Model model) throws Exception{
        List<Manufacturer> manufacturers = manufacturerService.list();
        model.addAttribute("manufacturerList",manufacturers);
        return "areaDevice/device/switchIndex";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serverName",value = "服务器名称",required = true,dataType = "String"),
            @ApiImplicitParam(name = "position",value = "位置",required = true,dataType = "String"),
            @ApiImplicitParam(name = "manufacturerId",value = "厂商id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "deviceTypeId",value = "设备类型id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "ip",value = "ip地址",required = true,dataType = "String"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @PreAuthorize("hasAnyAuthority('deviceserver:query','devicestorage:query','devicewhole:query','deviceswitch:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list(String serverName,String position, Integer manufacturerId, Integer deviceTypeId, String ip,Boolean isTrust,Boolean isVm,String orderByClause, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("serverName",serverName);
            map.put("position",position);
            map.put("deviceTypeId",deviceTypeId);
            map.put("manufacturerId",manufacturerId);
            map.put("ip",ip);
            map.put("isTrust",isTrust);
            map.put("isVm",isVm);
            map.put("orderByClause",orderByClause==null?"serverNo desc":orderByClause.replace("'",""));
            List<Device> list = deviceService.list(map,page,rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //详细信息
    @ApiOperation("服务器详情视图")
    @ApiImplicitParam(name = "id",value = "设备id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('deviceserver:detail')")
    @GetMapping("/serverDetail")
    public String serverDetail(String id, Model model) throws Exception {
        Device device = deviceService.detail(id);
        model.addAttribute("model",device);
        return "areaDevice/device/serverDetail";
    }
    //详细信息
    @ApiOperation("存储阵列详情视图")
    @ApiImplicitParam(name = "id",value = "设备id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('devicestorage:detail')")
    @GetMapping("/storageDetail")
    public String storageDetail(String id, Model model) throws Exception {
        Device device = deviceService.detail(id);
        model.addAttribute("model",device);
        return "areaDevice/device/storageDetail";
    }
    //详细信息
    @ApiOperation("一体化设备详情视图")
    @ApiImplicitParam(name = "id",value = "设备id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('devicewhole:detail')")
    @GetMapping("/wholeDetail")
    public String deviceDetail(String id, Model model) throws Exception {
        Device device = deviceService.detail(id);
        model.addAttribute("model",device);
        return "areaDevice/device/wholeDetail";
    }
    //详细信息
    @ApiOperation("交换机详情视图")
    @ApiImplicitParam(name = "id",value = "设备id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('deviceswitch:detail')")
    @GetMapping("/switchDetail")
    public String switchDetail(String id, Model model) throws Exception {
        Device device = deviceService.detail(id);
        model.addAttribute("model",device);
        return "areaDevice/device/switchDetail";
    }

    //新增视图
    @ApiOperation("新增视图")
    @ApiImplicitParam(name = "deviceBatchId",value = "设备批次id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('devicebatch:device')")
    @GetMapping("/create")
    public String create(String deviceBatchId,Model model) throws Exception {
        DeviceBatch deviceBatch = deviceBatchService.detail(deviceBatchId);
        model.addAttribute("deviceBatch",deviceBatch);

        List<Os> osList = osService.list();
        model.addAttribute("osList",osList);
        List<Department> departmentList = departmentService.list();
        model.addAttribute("departmentList",departmentList);
        List<Room> roomList = roomService.list();
        model.addAttribute("roomList",roomList);
        return "areaDevice/device/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "设备实体类",required = true,dataType = "Device")
    @PreAuthorize("hasAnyAuthority('devicebatch:device')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Device entity) throws Exception{
        try {
            entity.setStatus(1);
            entity.setId(UUID.randomUUID().toString().replace("-",""));
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            if(entity.getAvailableStorage() == null){
                entity.setAvailableStorage(0.0);
            }

            deviceService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("生成设备编码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceBatchId",value = "设备批次id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "batchNo",value = "批次编码",required = true,dataType = "String")
    })
    @GetMapping("/lastDeviceNo")
    @ResponseBody
    public JsonMsg lastDeviceNo(String deviceBatchId, String batchNo){
        try {
            String deviceNo = deviceService.lastDeviceNo(deviceBatchId);
            if(StringUtils.isBlank(deviceNo)){
                deviceNo = batchNo+ "001";
            }
            else{
                String number = deviceNo.substring(7);
                if(StringUtils.isNotBlank(number) && StringUtils.isNumeric(number)){
                    deviceNo = batchNo + StringUtils.leftPad(Integer.toString(Integer.valueOf(number)+1),3,'0');
                }
            }
            return JsonMsg.success().add("deviceNo",deviceNo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @PreAuthorize("hasAnyAuthority('deviceserver:edit','devicestorage:edit','devicewhole:edit','deviceswitch:edit')")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        Device entity = deviceService.detail(id);
        model.addAttribute("model",entity);

        List<Os> osList = osService.list();
        model.addAttribute("osList",osList);
        List<Department> departmentList = departmentService.list(null);
        model.addAttribute("departmentList",departmentList);
        List<Room> roomList = roomService.list();
        model.addAttribute("roomList",roomList);

        return "areaDevice/device/edit";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "设备实体类",required = true,dataType = "Device")
    @PreAuthorize("hasAnyAuthority('deviceserver:edit','devicestorage:edit','devicewhole:edit','deviceswitch:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(Device entity) throws Exception {
        try {
            deviceService.update(entity);
            return JsonMsg.success();
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
    @PreAuthorize("hasAnyAuthority('deviceserver:excel','devicestorage:excel','devicewhole:excel','deviceswitch:excel')")
    @GetMapping("download")
    public void download(String serverName,String position, Integer manufacturerId, Integer deviceTypeId, String ip,HttpServletResponse response) throws Exception {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("serverName",serverName);
        map.put("position",position);
        map.put("deviceTypeId",deviceTypeId);
        map.put("manufacturerId",manufacturerId);
        map.put("ip",ip);

        Set<String> excludeColumnFiledNames = new HashSet<String>();

        String name = "未选择设备类型";
        if(deviceTypeId.equals(1)){
            name = "物理服务器列表";
            excludeColumnFiledNames.add("availableStorage");
            excludeColumnFiledNames.add("switchType");
        }
        else if(deviceTypeId.equals(2)){
            name = "存储阵列列表";
            excludeColumnFiledNames.add("switchType");
        }
        else if(deviceTypeId.equals(3)){
            name = "一体化设备列表";
            excludeColumnFiledNames.add("availableStorage");
            excludeColumnFiledNames.add("switchType");
        }
        else if(deviceTypeId.equals(4)){
            name = "交换机列表";
            excludeColumnFiledNames.add("availableStorage");
        }

        List<DeviceExcel> list = deviceService.listExcel(map);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DeviceExcel.class)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).excludeColumnFiledNames(excludeColumnFiledNames)
                .sheet("数据")
                .doWrite(list);
    }
}
