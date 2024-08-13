package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.ApplicationKind;

import java.util.List;


public interface  IApplicationKindService {
    public List<ApplicationKind> list() throws Exception;

    public ApplicationKind detail(int id) throws Exception;

    public int insert(ApplicationKind entity) throws Exception;

    public int update(ApplicationKind entity) throws Exception;

    public int delete(int id) throws Exception;

    public int deleteByIds(int[] ids) throws Exception;
}
