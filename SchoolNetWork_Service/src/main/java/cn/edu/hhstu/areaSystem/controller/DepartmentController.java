package cn.edu.hhstu.areaSystem.controller;

import cn.edu.hhstu.pojo.Department;
import cn.edu.hhstu.areaSystem.service.IDepartmentService;
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

@Api(tags = "部门管理")
@Controller
@RequestMapping("department")
public class  DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaSystem/department/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @ApiImplicitParam(name = "departmentName",value = "部门名称",required = false,dataType = "String")
    @PreAuthorize("hasAnyAuthority('department:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list(String departmentName) throws Exception{
        try {
            List<Department> list = departmentService.list(departmentName);
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('department:add')")
    @GetMapping("/create")
    public String create() {
        return "areaSystem/department/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "部门实体类",required = true,dataType = "Department")
    @PreAuthorize("hasAnyAuthority('department:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Department entity) throws Exception{
        try {
            departmentService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('department:edit')")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        Department entity = departmentService.detail(id);
        model.addAttribute("model",entity);
        return "areaSystem/department/edit";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "部门实体类",required = true,dataType = "Department")
    @PreAuthorize("hasAnyAuthority('department:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(Department entity) throws Exception {
        try {
            departmentService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "部门id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('department:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception {
        try {
            departmentService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "部门id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('department:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            departmentService.deleteByIds(strArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
