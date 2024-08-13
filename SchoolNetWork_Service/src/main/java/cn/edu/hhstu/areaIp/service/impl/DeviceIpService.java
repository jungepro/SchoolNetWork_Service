package cn.edu.hhstu.areaIp.service.impl;

import cn.edu.hhstu.areaIp.mapper.IDeviceIpMapper;
import cn.edu.hhstu.areaIp.service.IDeviceIpService;
import cn.edu.hhstu.pojo.DeviceIp;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class  DeviceIpService implements IDeviceIpService {

    @Autowired
    private IDeviceIpMapper deviceIpMapper;

    @Override
    public List<DeviceIp> listPage(String ip, Integer deviceTypeId,Integer openType, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return deviceIpMapper.list(ip,deviceTypeId,openType);
    }

    @Override
    public int insertDeviceIp(DeviceIp entity) throws Exception {
        return deviceIpMapper.insertDeviceIp(entity);
    }

    @Override
    public int deleteDeviceIp(int ipAddressId) {
        return deviceIpMapper.deleteDeviceIp(ipAddressId);
    }

    @Override
    public int deleteDeviceIpByIds(int[] ids) throws Exception {
        return deviceIpMapper.deleteDeviceIpByIds(ids);
    }
}
