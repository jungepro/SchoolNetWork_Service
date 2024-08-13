package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.ApplicationType;

import java.util.List;

public interface  IApplicationTypeService {
    public List<ApplicationType> list() throws Exception;

    public ApplicationType detail(int id) throws Exception;

    public int insert(ApplicationType entity) throws Exception;

    public int update(ApplicationType entity) throws Exception;

    public int delete(int id) throws Exception;

    public int deleteByIds(int[] ids) throws Exception;
}
