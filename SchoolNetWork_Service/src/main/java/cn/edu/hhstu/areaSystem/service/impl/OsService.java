package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IOsMapper;
import cn.edu.hhstu.areaSystem.service.IOsService;
import cn.edu.hhstu.pojo.Os;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  OsService implements IOsService {

    @Autowired
    private IOsMapper osMapper;

    @Override
    public List<Os> list() throws Exception {
        return osMapper.list();
    }

    @Override
    public Os detail(int id) throws Exception {
        return osMapper.detail(id);
    }

    @Override
    public void insert(Os entity) throws Exception {
        osMapper.insert(entity);
    }

    @Override
    public void update(Os entity) throws Exception {
        osMapper.update(entity);
    }

    @Override
    public void delete(int id) throws Exception {
        osMapper.delete(id);
    }

    @Override
    public void deleteByIds(int[] ids) throws Exception {
        osMapper.deleteByIds(ids);
    }
}
