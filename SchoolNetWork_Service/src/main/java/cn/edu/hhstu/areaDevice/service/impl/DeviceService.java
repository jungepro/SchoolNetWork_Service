package cn.edu.hhstu.areaDevice.service.impl;

import cn.edu.hhstu.areaDevice.service.IDeviceService;
import cn.edu.hhstu.areaDevice.mapper.IDeviceMapper;
import cn.edu.hhstu.entity.Excel.DeviceExcel;
import cn.edu.hhstu.pojo.Device;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class  DeviceService implements IDeviceService {

    @Autowired
    private IDeviceMapper deviceMapper;

    @Override
    public List<Device> list(HashMap params, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return deviceMapper.list(params);
    }

    @Override
    public List<DeviceExcel> listExcel(HashMap params) throws Exception {
        return deviceMapper.listExcel(params);
    }

    @Override
    public Device detail(String id) throws Exception {
        return deviceMapper.detail(id);
    }

    @Override
    public int insert(Device entity) throws Exception {
        return deviceMapper.insert(entity);
    }

    @Override
    public int update(Device entity) throws Exception {
        return deviceMapper.update(entity);
    }

    @Override
    public String lastDeviceNo(String deviceBatchId) throws Exception {
        String deviceNo = "";
        Device device = deviceMapper.lastDeviceNo(deviceBatchId);
        if(device != null){
            deviceNo = device.getServerNo();
        }
        return deviceNo;
    }
}
