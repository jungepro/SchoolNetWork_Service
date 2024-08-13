package cn.edu.hhstu.areaApplication.service;

import cn.edu.hhstu.pojo.ApplicationUrl;

import java.util.List;

public interface  IApplicationUrlService {

    public List<ApplicationUrl> list(String applicationId) throws Exception;

    public ApplicationUrl detail(int id) throws Exception;

    public int insert(ApplicationUrl entity) throws Exception;

    public int update(ApplicationUrl entity) throws Exception;

    public int delete(int id) throws Exception;
}
