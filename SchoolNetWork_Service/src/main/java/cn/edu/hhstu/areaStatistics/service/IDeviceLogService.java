package cn.edu.hhstu.areaStatistics.service;

import cn.edu.hhstu.pojo.DeviceLog;

import java.util.HashMap;
import java.util.List;

public interface IDeviceLogService {
    public List<DeviceLog> list(HashMap params) throws Exception;
    public List<DeviceLog> list(String deviceId) throws Exception;
    public DeviceLog detail(int id) throws Exception;
    public int insert(DeviceLog entity) throws Exception;
    public int update(DeviceLog entity) throws Exception;
    public int delete(int id) throws Exception;
}
