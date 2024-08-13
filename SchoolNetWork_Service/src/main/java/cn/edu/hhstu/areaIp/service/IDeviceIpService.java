package cn.edu.hhstu.areaIp.service;

import cn.edu.hhstu.pojo.DeviceIp;

import java.util.List;

public interface  IDeviceIpService {
    public List<DeviceIp> listPage(String ip, Integer deviceTypeId,Integer openType, Integer page, Integer rows) throws Exception;
    public int insertDeviceIp(DeviceIp entity) throws Exception;
    public int deleteDeviceIp(int ipAddressId);
    public int deleteDeviceIpByIds(int[] ids) throws Exception;
}
