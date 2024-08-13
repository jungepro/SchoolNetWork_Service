package cn.edu.hhstu.areaIp.controller;

import cn.edu.hhstu.pojo.IpDomain;
import cn.edu.hhstu.areaIp.service.IIpDomainService;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
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

@Api(tags = "IP域名管理")
@Controller
@RequestMapping("ipDomain")
public class  IpDomainController {

    @Autowired
    private IIpDomainService ipDomainService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaIp/ipDomain/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('ipdomain:query')")
    @PostMapping("/list")
    @ResponseBody
    public JsonMsg list() throws Exception{
        try {
            List<IpDomain> list = ipDomainService.list();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('ipdomain:add')")
    @GetMapping("/create")
    public String create() {
        return "areaIp/ipDomain/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "IP范畴实体类",required = true,dataType = "IpDomain")
    @PreAuthorize("hasAnyAuthority('ipdomain:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(IpDomain entity) throws Exception{
        try {
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            ipDomainService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "IP范畴id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ipdomain:edit')")
    @GetMapping("/edit")
    public String edit(int id, Model model) throws Exception {
        IpDomain entity = ipDomainService.detail(id);
        model.addAttribute("model",entity);
        return "areaIp/ipDomain/edit";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "IP范畴实体类",required = true,dataType = "IpDomain")
    @PreAuthorize("hasAnyAuthority('ipdomain:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(IpDomain entity) throws Exception {
        try {
            ipDomainService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "IP范畴id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ipdomain:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(int id) throws Exception {
        try {
            ipDomainService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
