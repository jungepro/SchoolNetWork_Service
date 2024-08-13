package cn.edu.hhstu.areaIp.service;

import cn.edu.hhstu.pojo.IpIsp;

import java.util.List;

public interface  IIpIspService {
    public List<IpIsp> list() throws Exception;
    public IpIsp detail(int id) throws Exception;
    public int insert(IpIsp entity) throws Exception;
    public int update(IpIsp entity) throws Exception;
    public int delete(int id) throws Exception;
}
