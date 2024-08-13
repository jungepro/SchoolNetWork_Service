package cn.edu.hhstu.areaApplication.service.impl;

import cn.edu.hhstu.areaApplication.mapper.IApplicationUrlMapper;
import cn.edu.hhstu.areaApplication.service.IApplicationUrlService;
import cn.edu.hhstu.pojo.ApplicationUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ApplicationUrlService implements IApplicationUrlService {

    @Autowired
    private IApplicationUrlMapper applicationUrlMapper;

    @Override
    public List<ApplicationUrl> list(String applicationId) throws Exception {
        return applicationUrlMapper.list(applicationId);
    }

    @Override
    public ApplicationUrl detail(int id) throws Exception {
        return applicationUrlMapper.detail(id);
    }

    @Override
    public int insert(ApplicationUrl entity) throws Exception {
        return applicationUrlMapper.insert(entity);
    }

    @Override
    public int update(ApplicationUrl entity) throws Exception {
        return applicationUrlMapper.update(entity);
    }

    @Override
    public int delete(int id) throws Exception {
        return applicationUrlMapper.delete(id);
    }
}
