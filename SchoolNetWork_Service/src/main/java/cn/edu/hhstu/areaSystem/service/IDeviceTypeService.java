package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.DeviceType;

import java.util.List;

public interface  IDeviceTypeService {
    public List<DeviceType> list() throws Exception;
    public List<DeviceType> list(Integer kind) throws Exception;

    public DeviceType detail(int id) throws Exception;
}
