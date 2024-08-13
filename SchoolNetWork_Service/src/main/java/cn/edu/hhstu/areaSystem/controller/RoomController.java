package cn.edu.hhstu.areaSystem.controller;

import cn.edu.hhstu.areaSystem.service.IDepartmentService;
import cn.edu.hhstu.areaSystem.service.IRoomService;
import cn.edu.hhstu.pojo.Department;
import cn.edu.hhstu.pojo.Room;
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
import java.util.UUID;


@Api(tags = "机房管理")
@Controller
@RequestMapping("room")
public class  RoomController {
    @Autowired
    private IRoomService roomService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaSystem/room/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('room:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list() throws Exception{
        try {
            List<Room> list = roomService.list();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('room:add')")
    @GetMapping("/create")
    public String create() {
        return "areaSystem/room/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "部门实体类",required = true,dataType = "Room")
    @PreAuthorize("hasAnyAuthority('room:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Room entity) throws Exception{
        try {
            entity.setId(UUID.randomUUID().toString().replace("-",""));
            roomService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('room:edit')")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        Room entity = roomService.detail(id);
        model.addAttribute("model",entity);
        return "areaSystem/room/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "机房实体类",required = true,dataType = "Room")
    @PreAuthorize("hasAnyAuthority('room:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(Room entity) throws Exception {
        try {
            roomService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "机房id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('room:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception {
        try {
            roomService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "机房id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('room:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            roomService.deleteByIds(strArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
