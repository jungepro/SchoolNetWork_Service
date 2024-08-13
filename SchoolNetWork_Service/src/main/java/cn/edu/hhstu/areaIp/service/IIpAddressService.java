package cn.edu.hhstu.areaIp.service;

import cn.edu.hhstu.pojo.DeviceIp;
import cn.edu.hhstu.pojo.IpAddress;

import java.util.List;

public interface  IIpAddressService {
    public List<IpAddress> list(String ip,Integer domainId,Integer openType, Integer page, Integer rows) throws Exception;

    public List<IpAddress> listDeviceIp(String deviceId) throws Exception;
    public List<IpAddress> deviceIps(Integer domainId, String ip,Integer openType, Integer page, Integer rows) throws Exception;

    public List<IpAddress> listUnUse(String ip, int top) throws Exception;

    public IpAddress detail(int id) throws Exception;

    public int existIp(String ip) throws Exception;

    public int insert(IpAddress entity) throws Exception;

    public int update(IpAddress entity) throws Exception;

//    public void remark(int id,String msg) throws Exception;

    public void delete(int id) throws Exception;

    public void deleteByIds(int[] ids) throws Exception;

    public int insertDeviceIp(DeviceIp entity) throws Exception;
    public int deleteDeviceIp(int ipAddressId) throws Exception;
    public int deleteDeviceIpByIds(int[] ids) throws Exception;
}
