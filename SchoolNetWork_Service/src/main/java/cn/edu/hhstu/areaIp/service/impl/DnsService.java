package cn.edu.hhstu.areaIp.service.impl;

import cn.edu.hhstu.areaIp.mapper.IDnsMapper;
import cn.edu.hhstu.areaIp.service.IDnsService;
import cn.edu.hhstu.pojo.Dns;
import cn.edu.hhstu.pojo.IpDns;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DnsService implements IDnsService {

    @Autowired
    private IDnsMapper dnsMapper;

    @Override
    public List<Dns> listPage(String domainName,Integer disabled, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return dnsMapper.list(domainName, disabled);
    }

    @Override
    public Dns detail(int id) throws Exception {
        return dnsMapper.detail(id);
    }

    @Override
    public int insert(Dns model) throws Exception {
        return dnsMapper.insert(model);
    }

    @Override
    public int update(Dns model) throws Exception {
        return dnsMapper.update(model);
    }

    @Override
    public int delete(int id) throws Exception {
        return dnsMapper.delete(id);
    }

    @Override
    public int offline(int id) throws Exception {
        return dnsMapper.offline(id);
    }

    @Override
    public int online(int id) throws Exception {
        return dnsMapper.online(id);
    }

    @Override
    public List<Dns> listUnuseDns(int ipAddressId) throws Exception {
        return dnsMapper.listUnuseDns(ipAddressId);
    }

    @Override
    public List<IpDns> listIpDns(int ipAddressId) throws Exception {
        return dnsMapper.listIpDns(ipAddressId);
    }

    @Override
    public int insertIpDns(Integer dnsId, Integer ipAddressId) throws Exception {
        return dnsMapper.insertIpDns(dnsId,ipAddressId);
    }

    @Override
    public int deleteIpDns(int dnsId, int ipAddressId) throws Exception {
        return dnsMapper.deleteIpDns(dnsId,ipAddressId);
    }
}
