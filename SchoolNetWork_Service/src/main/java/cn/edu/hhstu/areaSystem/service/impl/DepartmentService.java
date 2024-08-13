package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IDepartmentMapper;
import cn.edu.hhstu.pojo.Department;
import cn.edu.hhstu.areaSystem.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentMapper departmentMapper;


    @Override
    public List<Department> list(String departmentName) throws Exception {
        return departmentMapper.list(departmentName);
    }

    @Override
    public List<Department> list() throws Exception {
        return departmentMapper.list();
    }

    @Override
    public Department detail(String id) throws Exception {
        return departmentMapper.detail(id);
    }

    @Override
    public void insert(Department entity) throws Exception {
        departmentMapper.insert(entity);
    }

    @Override
    public void update(Department entity) throws Exception {
        departmentMapper.update(entity);
    }

    @Override
    public void delete(String id) throws Exception {
        departmentMapper.delete(id);
    }

    @Override
    public void deleteByIds(String[] ids) throws Exception {
        departmentMapper.deleteByIds(ids);
    }
}
