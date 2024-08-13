package cn.edu.hhstu.areaDevice.service.impl;

import cn.edu.hhstu.areaDevice.mapper.IDeviceParameterMapper;
import cn.edu.hhstu.areaDevice.service.IDeviceParameterService;
import cn.edu.hhstu.pojo.DeviceParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  DeviceParameterService implements IDeviceParameterService {

    @Autowired
    private IDeviceParameterMapper deviceParameterMapper;

    @Override
    public List<DeviceParameter> list(String deviceId) throws Exception {
        return deviceParameterMapper.list(deviceId);
    }

    @Override
    public DeviceParameter detail(int id) throws Exception {
        return deviceParameterMapper.detail(id);
    }

    @Override
    public int insert(DeviceParameter entity) throws Exception {
        return deviceParameterMapper.insert(entity);
    }

    @Override
    public int update(DeviceParameter entity) throws Exception {
        return deviceParameterMapper.update(entity);
    }

    @Override
    public int delete(int id) throws Exception {
        return deviceParameterMapper.delete(id);
    }
}
