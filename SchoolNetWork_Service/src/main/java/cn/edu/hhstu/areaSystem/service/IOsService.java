package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.Os;

import java.util.List;

public interface  IOsService {
    public List<Os> list() throws Exception;

    public Os detail(int id) throws Exception;

    public void insert(Os entity) throws Exception;

    public void update(Os entity) throws Exception;

    public void delete(int id) throws Exception;

    public void deleteByIds(int[] ids) throws Exception;
}
