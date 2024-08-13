package cn.edu.hhstu.areaDocument.service;

import cn.edu.hhstu.pojo.Article;

import java.util.HashMap;
import java.util.List;

public interface  IArticleService {
    public List<Article> listPage(HashMap params, Integer page, Integer rows) throws Exception;
    public Article detail(String id) throws Exception;
    public int insert(Article entity) throws Exception;
    public int update(Article entity) throws Exception;
    public int delete(String id) throws Exception;
    public void deleteByIds(String[] ids) throws Exception;
}
