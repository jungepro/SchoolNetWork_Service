package cn.edu.hhstu.areaDocument.service.impl;

import cn.edu.hhstu.areaDocument.mapper.IArticleMapper;
import cn.edu.hhstu.areaDocument.service.IArticleService;
import cn.edu.hhstu.areaSystem.service.IArticleClassService;
import cn.edu.hhstu.pojo.Article;
import cn.edu.hhstu.pojo.ArticleClass;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class  ArticleService implements IArticleService {

    @Autowired
    private IArticleMapper articleMapper;

    @Override
    public List<Article> listPage(HashMap params, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return articleMapper.list(params);
    }

    @Override
    public Article detail(String id) throws Exception {
        return articleMapper.detail(id);
    }

    @Override
    public int insert(Article entity) throws Exception {
        return articleMapper.insert(entity);
    }

    @Override
    public int update(Article entity) throws Exception {
        return articleMapper.update(entity);
    }

    @Override
    public int delete(String id) throws Exception {
        return articleMapper.delete(id);
    }

    @Override
    public void deleteByIds(String[] ids) throws Exception {
        articleMapper.deleteByIds(ids);
    }
}
