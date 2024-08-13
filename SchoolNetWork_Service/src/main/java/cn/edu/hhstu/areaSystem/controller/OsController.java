package cn.edu.hhstu.areaSystem.controller;

import cn.edu.hhstu.areaSystem.service.IOsService;
import cn.edu.hhstu.pojo.Manufacturer;
import cn.edu.hhstu.pojo.Os;
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


@Api(tags = "操作系统管理")
@Controller
@RequestMapping("os")
public class  OsController {

    @Autowired
    IOsService osService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaSystem/os/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('os:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list() throws Exception{
        try {
            List<Os> list = osService.list();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('os:add')")
    @GetMapping("/create")
    public String create() {
        return "areaSystem/os/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "操作系统实体类",required = true,dataType = "Os")
    @PreAuthorize("hasAnyAuthority('os:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Os entity) throws Exception{
        try {
            osService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "操作系统id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('os:edit')")
    @GetMapping("/edit")
    public String edit(Integer id, Model model) throws Exception {
        Os entity = osService.detail(id);
        model.addAttribute("model",entity);
        return "areaSystem/os/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "操作系统实体类",required = true,dataType = "Os")
    @PreAuthorize("hasAnyAuthority('os:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(Os entity) throws Exception {
        try {
            osService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "操作系统id",required = true,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('os:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(Integer id) throws Exception {
        try {
            osService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "应用类型id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('os:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            osService.deleteByIds(intArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
