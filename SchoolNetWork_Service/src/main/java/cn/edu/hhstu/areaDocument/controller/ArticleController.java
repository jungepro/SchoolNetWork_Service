package cn.edu.hhstu.areaDocument.controller;

import cn.edu.hhstu.areaDocument.service.IArticleService;
import cn.edu.hhstu.areaSystem.service.IArticleClassService;
import cn.edu.hhstu.pojo.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Api(tags = "文档管理")
@Controller
@RequestMapping("article")
public class  ArticleController {

    @Autowired
    private IArticleClassService articleClassService;
    @Autowired
    private IArticleService articleService;

    @ApiOperation("列表视图")
    @GetMapping("/index")
    public String Index(Model model) throws Exception{
        List<ArticleClass> articleClassList = articleClassService.list(1);
        model.addAttribute("articleClassList",articleClassList);
        return "areaDocument/article/index";
    }

    @ApiOperation("列表数据接口")
    @PreAuthorize("hasAnyAuthority('article:query')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleClassId",value = "文档分类id",required = true,dataType = "string"),
            @ApiImplicitParam(name = "title",value = "文档标题",required = true,dataType = "string"),
            @ApiImplicitParam(name = "page",value = "分页页码",required = false,dataType = "Integer",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "分页大小",required = false,dataType = "Integer",defaultValue = "10")
    })
    @GetMapping("/listPage")
    @ResponseBody
    public JsonMsg listPage(String articleClassId,String title, @RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "rows",required = true,defaultValue = "10") Integer rows) throws Exception{
        try {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("articleClassId",articleClassId);
            map.put("title",title);
            List<Article> list = articleService.listPage(map, page, rows);
            PageInfo pageInfo = new PageInfo(list);
            return JsonMsg.success().add("pageInfo",pageInfo);
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("详情视图")
    @ApiImplicitParam(name = "id",value = "文档id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('article:detail')")
    @GetMapping("/detail")
    public String detail(String id, Model model) throws Exception {
        Article entity = articleService.detail(id);
        model.addAttribute("model",entity);
        return "areaDocument/article/detail";
    }

    @ApiOperation("新增视图")
    @PreAuthorize("hasAnyAuthority('article:add')")
    @GetMapping("/create")
    public String create(Model model) throws Exception {
        List<ArticleClass> articleClassList = articleClassService.list(1);
        model.addAttribute("articleClassList",articleClassList);
        String publisher = UserUtil.getLoginRealName();
        model.addAttribute("publisher",publisher);
        return "areaDocument/article/create";
    }

    @ApiOperation("新增保存接口")
    @ApiImplicitParam(name = "entity",value = "文档实体类",required = true,dataType = "Article")
    @PreAuthorize("hasAnyAuthority('article:add')")
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg insert(Article entity) throws Exception{
        try {
            entity.setId(UUID.randomUUID().toString().replace("-",""));
//            entity.setCreateTime(new Timestamp(new Date().getTime()));
            entity.setCreator(UserUtil.getLoginUserName());
            articleService.insert(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("编辑视图")
    @ApiImplicitParam(name = "id",value = "文档id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('article:edit')")
    @GetMapping("/edit")
    public String edit(String id, Model model) throws Exception {
        Article entity = articleService.detail(id);
        model.addAttribute("model",entity);

        List<ArticleClass> articleClassList = articleClassService.list(1);
        model.addAttribute("articleClassList",articleClassList);

        return "areaDocument/article/modify";
    }

    @ApiOperation("编辑保存接口")
    @ApiImplicitParam(name = "entity",value = "文档实体类",required = true,dataType = "Article")
    @PreAuthorize("hasAnyAuthority('article:edit')")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg update(Article entity) throws Exception {
        try {
            articleService.update(entity);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除单条数据接口")
    @ApiImplicitParam(name = "id",value = "文档id",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('article:delete')")
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public JsonMsg delete(String id) throws Exception {
        try {
            articleService.delete(id);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }

    @ApiOperation("删除多条数据接口")
    @ApiImplicitParam(name = "ids",value = "文档id，多条使用逗号隔开",required = true,dataType = "String")
    @PreAuthorize("hasAnyAuthority('article:delete')")
    @DeleteMapping(value = "/deleteByIds")
    @ResponseBody
    public JsonMsg deleteByIds(String ids) throws Exception {
        try {
            String[] strArr=ids.split(",");
            articleService.deleteByIds(strArr);
            return JsonMsg.success();
        }
        catch (Exception ex){
            return JsonMsg.resonpse(500,"服务器请求异常：" + ex.getCause());
        }
    }
}
