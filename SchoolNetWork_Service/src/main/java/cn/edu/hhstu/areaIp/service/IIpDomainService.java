package cn.edu.hhstu.areaIp.service;

import cn.edu.hhstu.pojo.IpDomain;

import java.util.List;

public interface  IIpDomainService {
    public List<IpDomain> list() throws Exception;

    public IpDomain detail(int id) throws Exception;

    public void insert(IpDomain entity) throws Exception;

    public void update(IpDomain entity) throws Exception;

    public void delete(int id) throws Exception;
}
