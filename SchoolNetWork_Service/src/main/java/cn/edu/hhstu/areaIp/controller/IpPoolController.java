package cn.edu.hhstu.areaIp.controller;

import cn.edu.hhstu.areaIp.service.IIpDomainService;
import cn.edu.hhstu.areaIp.service.IIpPoolService;
import cn.edu.hhstu.pojo.IpDomain;
import cn.edu.hhstu.pojo.IpPool;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import com.github.pagehelper.PageInfo;
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
import java.util.Date;
import java.util.List;

@Api(tags = "IP地址池管理")
@Controller
@RequestMapping("ipPool")
public class  IpPoolController {

    @Autowired
    private IIpPoolService ipPoolService;
    @Autowired
    private IIpDomainService ipDomainService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index(Model model) throws Exception{
        List<IpDomain> list = ipDomainService.list();
        model.addAttribute("ipDomainList",list);
        return "areaIp/ipPool/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity",value = "IP地址池实体类",required = true,dataType = "IpPool"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @PreAuthorize("hasAnyAuthority('ippool:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list(IpPool entity,@RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            List<IpPool> list = ipPoolService.list(entity,page,rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增视图
    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('ippool:add')")
    @GetMapping("/create")
    public String create(Model model) throws Exception {
        List<IpDomain> list = ipDomainService.list();
        model.addAttribute("ipDomainList",list);
        return "areaIp/ipPool/create";
    }

    //新增数据保存
    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "IP地址池实体类",required = true,dataType = "IpPool")
    @PreAuthorize("hasAnyAuthority('ippool:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(IpPool entity) throws Exception{
        try {
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            ipPoolService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //编辑视图
    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ippool:edit')")
    @GetMapping("/edit")
    public String edit(int id, Model model) throws Exception {
        IpPool entity = ipPoolService.detail(id);
        model.addAttribute("model",entity);
        List<IpDomain> list = ipDomainService.list();
        model.addAttribute("ipDomainList",list);
        return "areaIp/ipPool/edit";
    }

    //编辑数据保存
    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "IP地址池实体类",required = true,dataType = "IpPool")
    @PreAuthorize("hasAnyAuthority('ippool:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(IpPool entity) throws Exception {
        try {
            ipPoolService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除单条数据
    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "IP地址池id",required = true,dataType = "int")
    @PreAuthorize("hasAnyAuthority('ippool:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(int id) throws Exception {
        try {
            ipPoolService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //删除多条数据
    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "IP地址池id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('ippool:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            int[] intArr = new int[strArr.length];
            for (int i = 0; i<strArr.length; i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            ipPoolService.deleteByIds(intArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

}

