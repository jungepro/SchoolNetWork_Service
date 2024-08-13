package cn.edu.hhstu.security.controller;

import cn.edu.hhstu.security.entity.SysMenuEntity;
import cn.edu.hhstu.security.pojo.LoginRole;
import cn.edu.hhstu.security.pojo.SysMenu;
import cn.edu.hhstu.security.service.ILoginRoleService;
import cn.edu.hhstu.security.service.ISysMenuService;
import cn.edu.hhstu.utils.JsonMsg;
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

import java.util.List;

@Api(tags = "角色及权限管理")
@Controller
@RequestMapping("loginRole")
public class LoginRoleController {

    @Autowired
    private ILoginRoleService loginRoleService;
    @Autowired
    private ISysMenuService sysMenuService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "security/loginRole/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:query')")
    public JsonMsg list() throws Exception{
        try {
            List<LoginRole> list = loginRoleService.list();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority('loginrole:add')")
    public String create() {
        return "security/loginRole/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "角色实体类",required = true,dataType = "LoginRole")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:add')")
    public JsonMsg insert(LoginRole entity) throws Exception{
        try {
            loginRoleService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "角色id",required = true,dataType = "Integer")
    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('loginrole:edit')")
    public String edit(Integer id, Model model) throws Exception {
        LoginRole entity = loginRoleService.detail(id);
        model.addAttribute("model",entity);
        return "security/loginRole/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "角色实体类",required = true,dataType = "LoginRole")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:edit')")
    public JsonMsg update(LoginRole entity) throws Exception {
        try {
            loginRoleService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "角色id",required = true,dataType = "Integer")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:delete')")
    public JsonMsg delete(Integer id) throws Exception {
        try {
            if(id==0){
                return JsonMsg.fail();
            }
            loginRoleService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //角色权限视图
    @ApiOperation("角色授权视图")
    @ApiImplicitParam(name = "id",value = "角色id",required = true,dataType = "Integer")
    @GetMapping("/grant")
    @PreAuthorize("hasAnyAuthority('loginrole:grant')")
    public String grant(Integer id,Model model) throws Exception {
        model.addAttribute("roleId",id);
        return "security/loginRole/grant";
    }

    //角色权限接口
    @ApiOperation("获取指定角色权限列表接口")
    @ApiImplicitParam(name = "id",value = "角色id",required = true,dataType = "Integer")
    @GetMapping("permissions")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:grant')")
    public JsonMsg permittions(Integer id) throws Exception {
        try {
            List<SysMenu> ownMenu = sysMenuService.ownPermissions(id);
            List<SysMenuEntity> allMenu = sysMenuService.listByAll();
            for (SysMenuEntity menuEntity: allMenu){
                for (SysMenu own: ownMenu){
                    if(own.getId() == menuEntity.getId()){
                        menuEntity.setOwn(true);
                        break;
                    }
                    else{
                        menuEntity.setOwn(false);
                    }
                }
            }
            return JsonMsg.success().add("ownList",ownMenu).add("allList",allMenu);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //添加角色权限
    @ApiOperation("增加角色授权保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "menuIds",value = "权限菜单id，多个菜单项用逗号隔开",required = true,dataType = "String")
    })
    @PostMapping(value = "/insertPermission")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:grant')")
    public JsonMsg insertPermission(Integer roleId, String menuIds) {
        try {
            if(roleId==null || StringUtils.isBlank(menuIds)){
                return JsonMsg.fail();
            }
            String[] strArr=menuIds.split(",");
            for (int i = 0; i<strArr.length; i++){
                int menu = Integer.parseInt(strArr[i]);
                if(loginRoleService.existPermission(roleId,menu) == 0){
                    loginRoleService.insertPermission(roleId,menu);
                }
            }

            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
    @ApiOperation("增加全部角色授权保存接口")
    @ApiImplicitParam(name = "roleId",value = "角色id",required = true,dataType = "String")
    @PostMapping(value = "/insertAllPermissions")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:grant')")
    public JsonMsg insertAllPermissions(Integer roleId) {
        try {
            if(roleId==null){
                return JsonMsg.fail();
            }
            loginRoleService.insertAllPermissions(roleId);

            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
    //删除角色权限
    @ApiOperation("删除多条角色权限接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "menuIds",value = "权限菜单id，多个菜单项用逗号隔开",required = true,dataType = "String")
    })
    @DeleteMapping(value = "/deletePermissions")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:grant')")
    public JsonMsg deletePermissions(Integer roleId, String menuIds) {
        try {
            if(roleId==null || StringUtils.isBlank(menuIds)){
                return JsonMsg.fail();
            }
            String[] strArr=menuIds.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            loginRoleService.deletePermissions(roleId,intArr);

            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除全部角色权限接口")
    @ApiImplicitParam(name = "roleId",value = "角色id",required = true,dataType = "String")
    @DeleteMapping(value = "/deleteAllPermissions")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginrole:grant')")
    public JsonMsg deleteAllPermissions(Integer roleId) {
        try {
            if(roleId==null){
                return JsonMsg.fail();
            }
            loginRoleService.deleteAllPermissions(roleId);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
