package cn.edu.hhstu.areaStatistics.controller;

import cn.edu.hhstu.areaStatistics.service.IServerFigureService;
import cn.edu.hhstu.areaStatistics.service.impl.ServerFigureService;
import cn.edu.hhstu.entity.FigureEntity;
import cn.edu.hhstu.pojo.Device;
import cn.edu.hhstu.utils.JsonMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.View;
import java.util.List;

@Api(tags = "服务器数据管理")
@Controller
@RequestMapping("statistics")
public class ServerFigureController {
    @Autowired
    private IServerFigureService serverFigureService;

    @ApiOperation("服务器统计视图")
    @GetMapping("/serverFigure")
    public String serverIndex(Model model) throws Exception{
        return "areaStatistics/serverFigure";
    }

    @ApiOperation("物理和虚拟服务器总量统计接口")
//    @PreAuthorize("hasAnyAuthority('statistics:application')")
    @GetMapping(value = "/serverByTotal")
    @ResponseBody
    public JsonMsg byTotal() throws Exception {
        try {
            List<FigureEntity<Integer>> list = serverFigureService.byTotal();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("物理服务器年度入库统计接口")
    @GetMapping(value = "/byServerYear")
    @ResponseBody
    public JsonMsg byServerYear() throws Exception {
        try {
            List<FigureEntity<Integer>> list = serverFigureService.byServerYear();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("物理服务器厂商统计接口")
    @GetMapping(value = "/byManufacturer")
    @ResponseBody
    public JsonMsg byManufacturer() throws Exception {
        try {
            List<FigureEntity<Integer>> list = serverFigureService.byManufacturer();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("物理服务器虚拟化统计接口")
    @GetMapping(value = "/byIsVm")
    @ResponseBody
    public JsonMsg byIsVm() throws Exception {
        try {
            List<FigureEntity<Integer>> list = serverFigureService.byIsVm();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("物理服务器托管统计接口")
    @GetMapping(value = "/byIsTrust")
    @ResponseBody
    public JsonMsg byIsTrust() throws Exception {
        try {
            List<FigureEntity<Integer>> list = serverFigureService.byIsTrust();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("虚拟服务器年度创建统计接口")
    @GetMapping(value = "/byVmServerYear")
    @ResponseBody
    public JsonMsg byVmServerYear() throws Exception {
        try {
            List<FigureEntity<Integer>> list = serverFigureService.byVmServerYear();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("服务器操作系统统计接口")
    @GetMapping(value = "/byOs")
    @ResponseBody
    public JsonMsg byOs() throws Exception {
        try {
            List<FigureEntity<Integer>> list = serverFigureService.byOs();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
