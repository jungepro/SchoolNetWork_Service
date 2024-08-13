package cn.edu.hhstu.areaSystem.controller;

import cn.edu.hhstu.areaSystem.service.IApplicationKindService;
import cn.edu.hhstu.areaSystem.service.IApplicationTypeService;
import cn.edu.hhstu.pojo.ApplicationKind;
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

@Api(tags = "自建系统应用类型管理")
@Controller
@RequestMapping("applicationKind")
public class ApplicationKindController {
    @Autowired
    IApplicationKindService applicationKindService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaSystem/applicationKind/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('applicationkind:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list() throws Exception{
        try {
            List<ApplicationKind> list = applicationKindService.list();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('applicationkind:add')")
    @GetMapping("/create")
    public String create() {
        return "areaSystem/applicationKind/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "应用类型实体类",required = true,dataType = "ApplicationKind")
    @PreAuthorize("hasAnyAuthority('applicationkind:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(ApplicationKind entity) throws Exception{
        try {
            applicationKindService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "应用类型id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('applicationkind:edit')")
    @GetMapping("/edit")
    public String edit(Integer id, Model model) throws Exception {
        ApplicationKind entity = applicationKindService.detail(id);
        model.addAttribute("model",entity);
        return "areaSystem/applicationKind/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "应用类型实体类",required = true,dataType = "ApplicationKind")
    @PreAuthorize("hasAnyAuthority('applicationkind:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(ApplicationKind entity) throws Exception {
        try {
            applicationKindService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "应用类型id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('applicationkind:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(Integer id) throws Exception {
        try {
            applicationKindService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "应用类型id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('applicationkind:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            applicationKindService.deleteByIds(intArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
