package cn.edu.hhstu.areaApplication.controller;

import cn.edu.hhstu.areaApplication.service.IApplicationUrlService;
import cn.edu.hhstu.pojo.Application;
import cn.edu.hhstu.pojo.ApplicationType;
import cn.edu.hhstu.pojo.ApplicationUrl;
import cn.edu.hhstu.utils.JsonMsg;
import cn.edu.hhstu.utils.UserUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Api(tags = "应用系统Url管理")
@Controller
@RequestMapping("applicationUrl")
public class  ApplicationUrlController {

    @Autowired
    private IApplicationUrlService applicationUrlService;

    //列表数据视图
    @ApiOperation("列表视图")
    @ApiImplicitParam(name = "appId",value = "应用系统id",required = true,dataType = "String")
    @GetMapping("/index")
    public String index(String appId,Model model) throws Exception {
        model.addAttribute("appId",appId);
        return "areaApplication/applicationUrl/index";
    }

    //列表数据json接口
    @ApiOperation("列表数据接口")
    @ApiImplicitParam(name = "applicationId",value = "应用系统id",required = true,dataType = "String")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg listPage(String applicationId) throws Exception {
        try {
            List<ApplicationUrl> list = applicationUrlService.list(applicationId);
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    //新增数据保存接口
    @ApiOperation("新增保存接口")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(ApplicationUrl entity) throws Exception{
        try {
            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());

            applicationUrlService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除单条数据接口")
    @DeleteMapping("/delete")
    @ResponseBody
    public JsonMsg delete(int id) throws Exception {
        try {
            applicationUrlService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex) {
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
