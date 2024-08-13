package cn.edu.hhstu.areaSystem.controller;

import cn.edu.hhstu.areaSystem.service.IArticleClassService;
import cn.edu.hhstu.pojo.ArticleClass;
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

@Api(tags = "文档分类管理")
@Controller
@RequestMapping("articleClass")
public class ArticleClassController {

    @Autowired
    private IArticleClassService articleClassService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index() throws Exception{
        return "areaSystem/articleClass/index";
    }

    @ApiOperation("列表数据接口")
    @ApiImplicitParam(name = "category",value = "分类范畴",required = false,dataType = "Integer")
    @PreAuthorize("hasAnyAuthority('articleclass:query')")
    @GetMapping("/list")
    @ResponseBody
    public JsonMsg list(Integer category) throws Exception{
        try {
            List<ArticleClass> list = articleClassService.list(category);
            return JsonMsg.success().add("list",list);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('articleclass:add')")
    @GetMapping("/create")
    public String create() {
        return "areaSystem/articleClass/create";
    }

    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "文档分类实体类",required = true,dataType = "ArticleClass")
    @PreAuthorize("hasAnyAuthority('articleclass:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(ArticleClass entity) throws Exception{
        try {
//            entity.setId(UUID.randomUUID().toString().replace("-",""));
            entity.setCategory(1);
            articleClassService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "数据id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('articleclass:edit')")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        ArticleClass entity = articleClassService.detail(id);
        model.addAttribute("model",entity);
        return "areaSystem/articleClass/modify";
    }

    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "文档类型实体类",required = true,dataType = "ArticleClass")
    @PreAuthorize("hasAnyAuthority('articleclass:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(ArticleClass entity) throws Exception {
        try {
            articleClassService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "文档分类id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('articleclass:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception {
        try {
            articleClassService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
