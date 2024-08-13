package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IDeviceTypeMapper;
import cn.edu.hhstu.areaSystem.service.IDeviceTypeService;
import cn.edu.hhstu.pojo.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  DeviceTypeService implements IDeviceTypeService {

    @Autowired
    private IDeviceTypeMapper deviceTypeMapper;

    @Override
    public List<DeviceType> list() throws Exception {
        return deviceTypeMapper.list();
    }

    @Override
    public List<DeviceType> list(Integer kind) throws Exception {
        return deviceTypeMapper.list(kind);
    }

    @Override
    public DeviceType detail(int id) throws Exception {
        return deviceTypeMapper.detail(id);
    }
}
