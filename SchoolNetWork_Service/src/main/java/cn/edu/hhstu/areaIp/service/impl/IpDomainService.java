package cn.edu.hhstu.areaIp.service.impl;

import cn.edu.hhstu.areaIp.mapper.IIpDomainMapper;
import cn.edu.hhstu.areaIp.service.IIpDomainService;
import cn.edu.hhstu.pojo.IpDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  IpDomainService implements IIpDomainService {

    @Autowired
    private IIpDomainMapper ipDomainMapper;

    @Override
    public List<IpDomain> list() throws Exception {
        return ipDomainMapper.list();
    }

    @Override
    public IpDomain detail(int id) throws Exception {
        return ipDomainMapper.detail(id);
    }

    @Override
    public void insert(IpDomain entity) throws Exception {
        ipDomainMapper.insert(entity);
    }

    @Override
    public void update(IpDomain entity) throws Exception {
        ipDomainMapper.update(entity);
    }

    @Override
    public void delete(int id) throws Exception {
        ipDomainMapper.delete(id);
    }
}
