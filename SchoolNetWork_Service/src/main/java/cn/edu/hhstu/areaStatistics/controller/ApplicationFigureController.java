package cn.edu.hhstu.areaStatistics.controller;

import cn.edu.hhstu.areaStatistics.service.IApplicationFigureService;
import cn.edu.hhstu.entity.FigureEntity;
import cn.edu.hhstu.utils.JsonMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Api(tags = "应用系统数据管理")
@Controller
@RequestMapping("statistics")
public class  ApplicationFigureController {

    @Autowired
    private IApplicationFigureService applicationFigureService;

    @ApiOperation("应用系统统计视图")
    @GetMapping("/applicationFigure")
    public String applicationIndex(Model model) throws Exception{
        return "areaStatistics/applicationFigure";
    }

    @ApiOperation("应用分类统计接口")
    @GetMapping(value = "/byApplicationType")
    @ResponseBody
    public JsonMsg byApplicationType() throws Exception {
        try {
            List<FigureEntity<Integer>> list = applicationFigureService.byApplicationType();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("运维部门统计接口")
    @GetMapping(value = "/byDepartment")
    @ResponseBody
    public JsonMsg byDepartment() throws Exception {
        try {
            List<FigureEntity<Integer>> list = applicationFigureService.byDepartment();
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
