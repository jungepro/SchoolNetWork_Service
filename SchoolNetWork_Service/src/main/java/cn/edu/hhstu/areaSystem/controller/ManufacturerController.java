package cn.edu.hhstu.areaSystem.controller;

import cn.edu.hhstu.areaSystem.service.IManufacturerService;
import cn.edu.hhstu.pojo.Department;
import cn.edu.hhstu.pojo.Manufacturer;
import cn.edu.hhstu.utils.JsonMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "设备厂商管理")
@Controller
@RequestMapping("manufacturer")
public class  ManufacturerController {

    @Autowired
    IManufacturerService manufacturerService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaSystem/manufacturer/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('manufacturer:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list() throws Exception{
        try {
            List<Manufacturer> list = manufacturerService.list();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('manufacturer:add')")
    @GetMapping("/create")
    public String create() {
        return "areaSystem/manufacturer/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "设备厂商实体类",required = true,dataType = "Manufacturer")
    @PreAuthorize("hasAnyAuthority('manufacturer:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Manufacturer entity) throws Exception{
        try {
            manufacturerService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "设备厂商id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('manufacturer:edit')")
    @GetMapping("/edit")
    public String edit(Integer id, Model model) throws Exception {
        Manufacturer entity = manufacturerService.detail(id);
        model.addAttribute("model",entity);
        return "areaSystem/manufacturer/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "设备厂商实体类",required = true,dataType = "Manufacturer")
    @PreAuthorize("hasAnyAuthority('manufacturer:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(Manufacturer entity) throws Exception {
        try {
            manufacturerService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据视图")
    @ApiImplicitParam(name = "id",value = "设备厂商id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('manufacturer:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(Integer id) throws Exception {
        try {
            manufacturerService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "设备厂商id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('manufacturer:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            manufacturerService.deleteByIds(intArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
