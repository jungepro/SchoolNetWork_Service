package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.ArticleClass;

import java.util.List;


public interface  IArticleClassService {
    public List<ArticleClass> list(Integer category) throws Exception;
    public List<ArticleClass> list() throws Exception;
    public ArticleClass detail(String id) throws Exception;
    public int insert(ArticleClass entity) throws Exception;
    public int update(ArticleClass entity) throws Exception;
    public int delete(String id) throws Exception;
}
