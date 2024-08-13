package cn.edu.hhstu.security.controller;

import cn.edu.hhstu.areaSystem.service.IDepartmentService;
import cn.edu.hhstu.pojo.IpDomain;
import cn.edu.hhstu.security.pojo.LoginRole;
import cn.edu.hhstu.security.pojo.LoginUser;
import cn.edu.hhstu.security.service.ILoginRoleService;
import cn.edu.hhstu.security.service.ILoginUserService;
import cn.edu.hhstu.utils.JsonMsg;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@Api(tags = "用户管理")
@Controller
@RequestMapping("loginUser")
public class LoginUserController {

    @Autowired
    private ILoginUserService loginUserService;
    @Autowired
    private ILoginRoleService loginRoleService;
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index(Model model) throws Exception{
        model.addAttribute("departmentList",departmentService.list());
        return "security/loginUser/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity",value = "用户信息实体类",required = true,dataType = "LoginUser"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginuser:query')")
    public JsonMsg list(String account,String realName,String departmentId,Boolean disabled,Boolean ssoLogin,@RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("account",account);
            map.put("realName",realName);
            map.put("departmentId",departmentId);
            map.put("disabled",disabled);
            map.put("ssoLogin",ssoLogin);
            List<LoginUser> list = loginUserService.list(map, page, rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority('loginuser:add')")
    public String create(Model model) throws Exception {
        model.addAttribute("departmentList",departmentService.list());
        return "security/loginUser/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "用户信息实体类",required = true,dataType = "LoginUser")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginuser:add')")
    public JsonMsg insert(LoginUser entity) throws Exception{
        try {
            entity.setUserId(UUID.randomUUID().toString().replace("-",""));
            loginUserService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String")
    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('loginuser:edit')")
    public String edit(String id, Model model) throws Exception {
        LoginUser entity = loginUserService.detail(id);
        model.addAttribute("model",entity);
        model.addAttribute("departmentList",departmentService.list());
        return "security/loginUser/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "用户信息实体类",required = true,dataType = "LoginUser")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginuser:edit')")
    public JsonMsg update(LoginUser entity) throws Exception {
        try {
            loginUserService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginuser:delete')")
    public JsonMsg delete(String id) throws Exception {
        try {
            loginUserService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //修改用户密码视图
    @ApiOperation("修改密码视图")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String")
    @GetMapping("/pwd")
    @PreAuthorize("hasAnyAuthority('loginuser:password')")
    public String pwd(String id,Model model) throws Exception {
        LoginUser entity = loginUserService.detail(id);
        model.addAttribute("model",entity);
        return "security/loginUser/pwd";
    }

    //保存用户密码
    @ApiOperation("修改密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "pwd",value = "新密码",required = true,dataType = "String")
    })
    @PostMapping("/savePwd")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginuser:password')")
    public JsonMsg savePwd(String userId,String pwd) {
        try {
            loginUserService.savePwd(userId,pwd);
            return JsonMsg.success();
        } catch (Exception ex) {
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //角色视图
    @ApiOperation("用户角色修改视图")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String")
    @GetMapping("/role")
    @PreAuthorize("hasAnyAuthority('loginuser:role')")
    public String role(String id,Model model) throws Exception {
        LoginUser entity = loginUserService.detail(id);
        model.addAttribute("model",entity);

        List<LoginRole> list = loginRoleService.list();
        model.addAttribute("roleList",list);

        ArrayList<Integer> roles = new ArrayList<>();
        for(LoginRole item:entity.getLoginRoles()){
            roles.add(item.getRoleId());
        }
        model.addAttribute("roles",roles);

        return "security/loginUser/role";
    }

    //保存角色
    @ApiOperation("用户角色修改保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "add",value = "增加的角色id，多个时用逗号分隔",required = true,dataType = "String"),
            @ApiImplicitParam(name = "subtract",value = "删除的角色id，多个时用逗号分隔",required = true,dataType = "String")
    })
    @PostMapping("/saveRole")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('loginuser:role')")
    public JsonMsg saveRole(String userId,String add,String subtract) {
        try {
            if(StringUtils.isNotBlank(add)){
                String[] addArr = add.split(",");
                for (int i=0; i<addArr.length; i++) {
                    loginUserService.insertRole(userId,Integer.parseInt(addArr[i]));
                }
            }
            if(StringUtils.isNotBlank(subtract)){
                String[] subtractArr = subtract.split(",");
                for (int i=0; i<subtractArr.length; i++) {
                    loginUserService.deleteRole(userId,Integer.parseInt(subtractArr[i]));
                }
            }
            return JsonMsg.success();
        } catch (Exception ex) {
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
