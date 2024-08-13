package cn.edu.hhstu.areaDevice.controller;

import cn.edu.hhstu.areaDevice.service.IDeviceBatchService;
import cn.edu.hhstu.areaSystem.service.IDeviceTypeService;
import cn.edu.hhstu.areaSystem.service.IManufacturerService;
import cn.edu.hhstu.entity.Excel.DeviceBatchExcel;
import cn.edu.hhstu.pojo.DeviceBatch;
import cn.edu.hhstu.pojo.DeviceType;
import cn.edu.hhstu.pojo.Manufacturer;
import cn.edu.hhstu.utils.DateUtil;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api(tags = "设备出入库管理")
@Controller
@RequestMapping("deviceBatch")
public class  DeviceBatchController {

    @Autowired
    private IDeviceBatchService deviceBatchService;
    @Autowired
    private IDeviceTypeService deviceTypeService;
    @Autowired
    private IManufacturerService manufacturerService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index(Model model) throws Exception{
        List<DeviceType> deviceTypes = deviceTypeService.list(11);
        List<Manufacturer> manufacturers = manufacturerService.list();
        model.addAttribute("deviceTypeList",deviceTypes);
        model.addAttribute("manufacturerList",manufacturers);
        return "areaDevice/deviceBatch/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('devicebatch:query')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity",value = "设备批次实体类",required = true,dataType = "DeviceBatch"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list(DeviceBatch entity, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            List<DeviceBatch> list = deviceBatchService.list(entity,page,rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //详细信息
    @ApiOperation("详情视图")
    @ApiImplicitParam(name = "id",value = "设备批次id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('devicebatch:detail')")
    @GetMapping("/detail")
    public String detail(String id, Model model) throws Exception {
        DeviceBatch deviceBatch = deviceBatchService.detail(id);
        model.addAttribute("model",deviceBatch);
        return "areaDevice/deviceBatch/detail";
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('devicebatch:add')")
    @GetMapping("/create")
    public String create(Model model) throws Exception {
        List<DeviceType> deviceTypes = deviceTypeService.list(11);
        List<Manufacturer> manufacturers = manufacturerService.list();
        model.addAttribute("deviceTypeList",deviceTypes);
        model.addAttribute("manufacturerList",manufacturers);
        return "areaDevice/deviceBatch/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "设备批次实体类",required = true,dataType = "DeviceBatch")
    @PreAuthorize("hasAnyAuthority('devicebatch:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(DeviceBatch entity) throws Exception{
        try {
            entity.setId(UUID.randomUUID().toString().replace("-",""));
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            deviceBatchService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "设备批次id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('devicebatch:edit')")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        DeviceBatch entity = deviceBatchService.detail(id);
        model.addAttribute("model",entity);

        List<DeviceType> deviceTypes = deviceTypeService.list(11);
        List<Manufacturer> manufacturers = manufacturerService.list();
        model.addAttribute("deviceTypeList",deviceTypes);
        model.addAttribute("manufacturerList",manufacturers);

        return "areaDevice/deviceBatch/edit";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "设备批次实体类",required = true,dataType = "DeviceBatch")
    @PreAuthorize("hasAnyAuthority('devicebatch:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(DeviceBatch entity) throws Exception {
        try {
            deviceBatchService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "设备批次id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('devicebatch:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception {
        try {
            deviceBatchService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //获取新增的批次编码
    @ApiOperation("获取新增的批次编码接口")
    @ApiImplicitParam(name = "type",value = "设备类型",required = true,dataType = "Integer")
    @GetMapping("/lastBatchNo")
    @ResponseBody
    public JsonMsg lastBatchNo(Integer type) throws Exception {
        try {
            String batchNo = "";
            String sign = "";
            if(type != null){
                DeviceType device = deviceTypeService.detail(type);
                if(device != null){
                    sign = device.getSign();
                }
            }

            if(sign.length() != 0){
                batchNo = sign + DateUtil.getNowYear() + "01";
                DeviceBatch deviceBatch = deviceBatchService.lastBatchNo(sign);
                if(deviceBatch != null){
                    String number = deviceBatch.getBatchNo().substring(5);
                    if(StringUtils.isNotBlank(number) && StringUtils.isNumeric(number)){
                        batchNo = sign + DateUtil.getNowYear() + StringUtils.leftPad(Integer.toString(Integer.valueOf(number)+1),2,"0");
                    }
                }
            }

            return JsonMsg.success().add("batchNo",batchNo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("导出excel")
    @ApiImplicitParam(name = "entity",value = "设备批次实体类",required = true,dataType = "DeviceBatch")
    @PreAuthorize("hasAnyAuthority('devicebatch:excel')")
    @GetMapping("download")
    public void download(DeviceBatch entity, HttpServletResponse response) throws Exception {
        List<DeviceBatchExcel> list = deviceBatchService.listExcel(entity);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("设备入库列表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DeviceBatchExcel.class).sheet("数据").doWrite(list);
    }

}

