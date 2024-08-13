package cn.edu.hhstu.areaIp.controller;

import cn.edu.hhstu.areaIp.service.IDnsService;
import cn.edu.hhstu.pojo.Dns;
import cn.edu.hhstu.pojo.IpDomain;
import cn.edu.hhstu.pojo.IpPool;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Api(tags = "域名管理")
@Controller
@RequestMapping("dns")
public class DnsController {

    @Autowired
    private IDnsService dnsService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaIp/dns/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('dns:query')")
    @GetMapping("/listPage")
    @ResponseBody
    public JsonMsg listPage(String domainName, Integer disabled, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            List<Dns> list = dnsService.listPage(domainName, disabled,page,rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('dns:add')")
    @GetMapping("/create")
    public String create(Model model) throws Exception {
        return "areaIp/dns/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "dns实体类",required = true,dataType = "Dns")
    @PreAuthorize("hasAnyAuthority('dns:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Dns entity) throws Exception{
        try {
            dnsService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('dns:edit')")
    @GetMapping("/edit")
    public String edit(int id, Model model) throws Exception {
        Dns detail = dnsService.detail(id);
        model.addAttribute("model",detail);
        return "areaIp/dns/modify";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "dns实体类",required = true,dataType = "Dns")
    @PreAuthorize("hasAnyAuthority('dns:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(Dns entity) throws Exception {
        try {
            dnsService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('dns:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(int id) throws Exception {
        try {
            dnsService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
