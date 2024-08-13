package cn.edu.hhstu.areaIp.service.impl;

import cn.edu.hhstu.areaIp.mapper.IIpISPMapper;
import cn.edu.hhstu.areaIp.service.IIpIspService;
import cn.edu.hhstu.pojo.IpIsp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class  IpIspService implements IIpIspService {

    @Autowired
    private IIpISPMapper ipISPMapper;

    @Override
    public List<IpIsp> list() throws Exception {
        return ipISPMapper.list();
    }

    @Override
    public IpIsp detail(int id) throws Exception {
        return ipISPMapper.detail(id);
    }

    @Override
    public int insert(IpIsp entity) throws Exception {
        return ipISPMapper.insert(entity);
    }

    @Override
    public int update(IpIsp entity) throws Exception {
        return ipISPMapper.update(entity);
    }

    @Override
    public int delete(int id) throws Exception {
        return ipISPMapper.delete(id);
    }
}
