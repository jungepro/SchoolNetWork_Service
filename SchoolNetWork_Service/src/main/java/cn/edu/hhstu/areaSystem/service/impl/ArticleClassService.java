package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IArticleClassMapper;
import cn.edu.hhstu.areaSystem.service.IArticleClassService;
import cn.edu.hhstu.pojo.ArticleClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ArticleClassService implements IArticleClassService {

    @Autowired
    private IArticleClassMapper articleClassMapper;

    @Override
    public List<ArticleClass> list(Integer category) throws Exception {
        return articleClassMapper.list(category);
    }

    @Override
    public List<ArticleClass> list() throws Exception {
        return articleClassMapper.list();
    }

    @Override
    public ArticleClass detail(String id) throws Exception {
        return articleClassMapper.detail(id);
    }

    @Override
    public int insert(ArticleClass entity) throws Exception {
        return articleClassMapper.insert(entity);
    }

    @Override
    public int update(ArticleClass entity) throws Exception {
        return articleClassMapper.update(entity);
    }

    @Override
    public int delete(String id) throws Exception {
        return articleClassMapper.delete(id);
    }
}
