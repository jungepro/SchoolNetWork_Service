package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IApplicationKindMapper;
import cn.edu.hhstu.areaSystem.service.IApplicationKindService;
import cn.edu.hhstu.pojo.ApplicationKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationKindService implements IApplicationKindService {

    @Autowired
    private IApplicationKindMapper applicationKindMapper;

    @Override
    public List<ApplicationKind> list() throws Exception {
        return applicationKindMapper.list();
    }

    @Override
    public ApplicationKind detail(int id) throws Exception {
        return applicationKindMapper.detail(id);
    }

    @Override
    public int insert(ApplicationKind entity) throws Exception {
        return applicationKindMapper.insert(entity);
    }

    @Override
    public int update(ApplicationKind entity) throws Exception {
        return applicationKindMapper.update(entity);
    }

    @Override
    public int delete(int id) throws Exception {
        return applicationKindMapper.delete(id);
    }

    @Override
    public int deleteByIds(int[] ids) throws Exception {
        return applicationKindMapper.deleteByIds(ids);
    }
}
