package cn.edu.hhstu.areaIp.service;

import cn.edu.hhstu.pojo.IpPool;

import java.util.List;

public interface  IIpPoolService {
    public List<IpPool> list(IpPool entity, Integer page, Integer rows) throws Exception;

    public IpPool detail(int id) throws Exception;

    public void insert(IpPool entity) throws Exception;

    public void update(IpPool entity) throws Exception;

    public void delete(int id) throws Exception;

    public void deleteByIds(int[] ids) throws Exception;
}
