package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.Department;

import java.util.List;

public interface  IDepartmentService {
    public List<Department> list(String departmentName) throws Exception;
    public List<Department> list() throws Exception;

    public Department detail(String id) throws Exception;

    public void insert(Department entity) throws Exception;

    public void update(Department entity) throws Exception;

    public void delete(String id) throws Exception;

    public void deleteByIds(String[] ids) throws Exception;
}
