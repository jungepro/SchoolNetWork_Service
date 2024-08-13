package cn.edu.hhstu.areaStatistics.service.impl;

import cn.edu.hhstu.areaStatistics.mapper.IDeviceLogMapper;
import cn.edu.hhstu.areaStatistics.service.IDeviceLogService;
import cn.edu.hhstu.pojo.DeviceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DeviceLogService implements IDeviceLogService {

    @Autowired
    private IDeviceLogMapper deviceLogMapper;

    @Override
    public List<DeviceLog> list(HashMap params) throws Exception {
        return null;
    }

    @Override
    public List<DeviceLog> list(String deviceId) throws Exception {
        return deviceLogMapper.list(deviceId);
    }

    @Override
    public DeviceLog detail(int id) throws Exception {
        return deviceLogMapper.detail(id);
    }

    @Override
    public int insert(DeviceLog entity) throws Exception {
        return deviceLogMapper.insert(entity);
    }

    @Override
    public int update(DeviceLog entity) throws Exception {
        return deviceLogMapper.update(entity);
    }

    @Override
    public int delete(int id) throws Exception {
        return deviceLogMapper.delete(id);
    }
}
