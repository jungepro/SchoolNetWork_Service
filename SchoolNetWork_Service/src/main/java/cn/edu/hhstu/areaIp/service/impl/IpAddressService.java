package cn.edu.hhstu.areaIp.service.impl;

import cn.edu.hhstu.areaIp.mapper.IIpAddressMapper;
import cn.edu.hhstu.areaIp.service.IIpAddressService;
import cn.edu.hhstu.pojo.DeviceIp;
import cn.edu.hhstu.pojo.IpAddress;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  IpAddressService implements IIpAddressService {

    @Autowired
    private IIpAddressMapper ipAddressMapper;

    @Override
    public List<IpAddress> list(String ip,Integer domainId,Integer openType, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return ipAddressMapper.list(ip,domainId,openType);
    }

    @Override
    public List<IpAddress> listDeviceIp(String deviceId) throws Exception {
        return ipAddressMapper.listDeviceIp(deviceId);
    }

    @Override
    public List<IpAddress> deviceIps(Integer domainId,String ip,Integer openType, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return ipAddressMapper.deviceIps(domainId,ip,openType);
    }


    @Override
    public List<IpAddress> listUnUse(String ip, int top) throws Exception {
        return ipAddressMapper.listUnUse(ip,top);
    }

    @Override
    public IpAddress detail(int id) throws Exception {
        return ipAddressMapper.detail(id);
    }

    @Override
    public int existIp(String ip) throws Exception {
        return ipAddressMapper.existIp(ip);
    }

    @Override
    public int insert(IpAddress entity) throws Exception {
        return ipAddressMapper.insert(entity);
    }

    @Override
    public int update(IpAddress entity) throws Exception {
        return ipAddressMapper.update(entity);
    }

//    @Override
//    public void remark(int id, String msg) throws Exception {
//        ipAddressMapper.remark(id,msg);
//    }

    @Override
    public void delete(int id) throws Exception {
        ipAddressMapper.delete(id);
    }

    @Override
    public void deleteByIds(int[] ids) throws Exception {
        ipAddressMapper.deleteByIds((ids));
    }

    @Override
    public int insertDeviceIp(DeviceIp entity) throws Exception {
        return ipAddressMapper.insertDeviceIp(entity);
    }

    @Override
    public int deleteDeviceIp(int ipAddressId) throws Exception {
        return ipAddressMapper.deleteDeviceIp(ipAddressId);
    }

    @Override
    public int deleteDeviceIpByIds(int[] ids) throws Exception {
        return ipAddressMapper.deleteDeviceIpByIds(ids);
    }

}
