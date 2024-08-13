package cn.edu.hhstu.security.controller;

import cn.edu.hhstu.security.entity.SysMenuEntity;
import cn.edu.hhstu.security.pojo.SysMenu;
import cn.edu.hhstu.security.service.ISysMenuService;
import cn.edu.hhstu.utils.JsonMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Api(tags = "权限菜单管理")
@Controller
@RequestMapping("sysMenu")
public class SysMenuController {

    @Autowired
    ISysMenuService sysMenuService;

    @ApiOperation("列表视图")
    @GetMapping("index")
    public String index() {
        return "security/sysMenu/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @GetMapping("/list")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sysmenu:query')")
    public JsonMsg list() throws Exception{
        try {
            List<SysMenu> list = sysMenuService.list();

            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid",value = "父id",required = true,dataType = "Integer")
    })
    @GetMapping("/create")
    @PreAuthorize("hasAnyAuthority('sysmenu:add')")
    public String create(Integer pid,Model model) throws Exception {
        SysMenu entity = null;
        if (pid==0){
            entity = new SysMenu();
            entity.setParentId(0);
            entity.setName("顶级菜单");
        }
        else{
            entity = sysMenuService.detail(pid);
        }
        model.addAttribute("parent",entity);
        return "security/sysMenu/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "菜单实体类",required = true,dataType = "SysMenu")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sysmenu:add')")
    public JsonMsg insert(SysMenu entity) throws Exception{
        try {
//            entity.setCreateTime(new Timestamp(new Date().getTime()));
            sysMenuService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "菜单id",required = true,dataType = "Integer")
    @GetMapping("/edit")
    @PreAuthorize("hasAnyAuthority('sysmenu:edit')")
    public String edit(Integer id, Model model) throws Exception {
        SysMenu detail = sysMenuService.detail(id);
        model.addAttribute("model",detail);

        if (detail.getParentId()==0){
            SysMenu entity = new SysMenu();
            entity.setParentId(0);
            entity.setName("顶级菜单");
            List<SysMenu> sysMenus = new ArrayList<>();
            sysMenus.add(entity);
            model.addAttribute("parents",sysMenus);
        }
        else{
            List<SysMenu> sysMenus = sysMenuService.parentList(detail.getParentId());
            model.addAttribute("parents",sysMenus);
        }
        return "security/sysMenu/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "菜单实体类",required = true,dataType = "SysMenu")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sysmenu:edit')")
    public JsonMsg update(SysMenu entity) throws Exception {
        try {
            sysMenuService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "菜单id",required = true,dataType = "Integer")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('sysmenu:delete')")
    public JsonMsg delete(Integer id) throws Exception {
        try {
            sysMenuService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

}
