package cn.edu.hhstu.areaIp.controller;

import cn.edu.hhstu.areaIp.service.IIpIspService;
import cn.edu.hhstu.pojo.IpIsp;
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

@Api(tags = "运营商线路管理")
@Controller
@RequestMapping("ipIsp")
public class IpIspController {

    @Autowired
    private IIpIspService ipIspService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index(Model model) throws Exception{
        return "areaIp/ipIsp/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('ipisp:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list() throws Exception{
        try {
            List<IpIsp> list = ipIspService.list();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('ipisp:add')")
    @GetMapping("/create")
    public String create(Model model) throws Exception {
        List<IpIsp> list = ipIspService.list();
        model.addAttribute("ipIspList",list);
        return "areaIp/ipIsp/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "运营商线路实体类",required = true,dataType = "IpIsp")
    @PreAuthorize("hasAnyAuthority('ipisp:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(IpIsp entity) throws Exception{
        try {
            ipIspService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ipisp:edit')")
    @GetMapping("/edit")
    public String edit(int id, Model model) throws Exception {
        IpIsp entity = ipIspService.detail(id);
        model.addAttribute("model",entity);
        return "areaIp/ipIsp/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "运营商线路实体类",required = true,dataType = "IpIsp")
    @PreAuthorize("hasAnyAuthority('ipisp:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(IpIsp entity) throws Exception {
        try {
            ipIspService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ipisp:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(int id) throws Exception {
        try {
            ipIspService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
