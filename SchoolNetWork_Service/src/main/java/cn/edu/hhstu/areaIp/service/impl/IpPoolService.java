package cn.edu.hhstu.areaIp.service.impl;

import cn.edu.hhstu.areaIp.mapper.IIpPoolMapper;
import cn.edu.hhstu.areaIp.service.IIpPoolService;
import cn.edu.hhstu.pojo.IpPool;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  IpPoolService implements IIpPoolService {

    @Autowired
    private IIpPoolMapper ipPoolMapper;

    @Override
    public List<IpPool> list(IpPool entity, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return ipPoolMapper.list(entity);
    }

    @Override
    public IpPool detail(int id) throws Exception {
        return ipPoolMapper.detail(id);
    }

    @Override
    public void insert(IpPool entity) throws Exception {
        ipPoolMapper.insert(entity);
    }

    @Override
    public void update(IpPool entity) throws Exception {
        ipPoolMapper.update(entity);
    }

    @Override
    public void delete(int id) throws Exception {
        ipPoolMapper.delete(id);
    }

    @Override
    public void deleteByIds(int[] ids) throws Exception {
        ipPoolMapper.deleteByIds(ids);
    }
}
