package cn.edu.hhstu.areaSystem.controller;

import cn.edu.hhstu.areaSystem.service.IApplicationTypeService;
import cn.edu.hhstu.pojo.ApplicationType;
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

@Api(tags = "应用类型管理")
@Controller
@RequestMapping("applicationType")
public class ApplicationTypeController {

    @Autowired
    IApplicationTypeService applicationTypeService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaSystem/applicationType/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('applicationtype:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list() throws Exception{
        try {
            List<ApplicationType> list = applicationTypeService.list();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('applicationtype:add')")
    @GetMapping("/create")
    public String create() {
        return "areaSystem/applicationType/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "应用类型实体类",required = true,dataType = "ApplicationType")
    @PreAuthorize("hasAnyAuthority('applicationtype:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(ApplicationType entity) throws Exception{
        try {
            applicationTypeService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "应用类型id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('applicationtype:edit')")
    @GetMapping("/edit")
    public String edit(Integer id, Model model) throws Exception {
        ApplicationType entity = applicationTypeService.detail(id);
        model.addAttribute("model",entity);
        return "areaSystem/applicationType/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "应用类型实体类",required = true,dataType = "ApplicationType")
    @PreAuthorize("hasAnyAuthority('applicationtype:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(ApplicationType entity) throws Exception {
        try {
            applicationTypeService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "应用类型id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('applicationtype:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(Integer id) throws Exception {
        try {
            applicationTypeService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "应用类型id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('applicationtype:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            applicationTypeService.deleteByIds(intArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
