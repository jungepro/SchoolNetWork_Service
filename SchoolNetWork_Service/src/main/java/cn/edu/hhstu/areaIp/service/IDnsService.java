package cn.edu.hhstu.areaIp.service;

import cn.edu.hhstu.pojo.Dns;
import cn.edu.hhstu.pojo.IpDns;

import java.util.List;


public interface  IDnsService {
    public List<Dns> listPage(String domainName, Integer disabled, Integer page, Integer rows) throws Exception;
    public Dns detail(int id) throws Exception;
    public int insert(Dns model) throws Exception;
    public int update(Dns model) throws Exception;
    public int delete(int id) throws Exception;
    public int offline(int id) throws Exception;
    public int online(int id) throws Exception;

    public List<Dns> listUnuseDns(int ipAddressId) throws Exception;
    public List<IpDns> listIpDns(int ipAddressId) throws Exception;
    public int insertIpDns(Integer dnsId,Integer ipAddressId) throws Exception;
    public int deleteIpDns(int dnsId,int ipAddressId) throws Exception;
}
