package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IApplicationTypeMapper;
import cn.edu.hhstu.areaSystem.service.IApplicationTypeService;
import cn.edu.hhstu.pojo.ApplicationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ApplicationTypeService implements IApplicationTypeService {

    @Autowired
    private IApplicationTypeMapper applicationTypeMapper;

    @Override
    public List<ApplicationType> list() throws Exception {
        return applicationTypeMapper.list();
    }

    @Override
    public ApplicationType detail(int id) throws Exception {
        return applicationTypeMapper.detail(id);
    }

    @Override
    public int insert(ApplicationType entity) throws Exception {
        return applicationTypeMapper.insert(entity);
    }

    @Override
    public int update(ApplicationType entity) throws Exception {
        return applicationTypeMapper.update(entity);
    }

    @Override
    public int delete(int id) throws Exception {
        return applicationTypeMapper.delete(id);
    }

    @Override
    public int deleteByIds(int[] ids) throws Exception {
        return applicationTypeMapper.deleteByIds(ids);
    }
}
