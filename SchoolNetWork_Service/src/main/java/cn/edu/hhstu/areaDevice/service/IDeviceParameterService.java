package cn.edu.hhstu.areaDevice.service;

import cn.edu.hhstu.pojo.DeviceParameter;

import java.util.List;


public interface  IDeviceParameterService {
    public List<DeviceParameter> list(String deviceId) throws Exception;

    public DeviceParameter detail(int id) throws Exception;

    public int insert(DeviceParameter entity) throws Exception;

    public int update(DeviceParameter entity) throws Exception;

    public int delete(int id) throws Exception;
}
