package cn.edu.hhstu.areaDevice.service.impl;

import cn.edu.hhstu.areaDevice.service.IDeviceBatchService;
import cn.edu.hhstu.areaDevice.mapper.IDeviceBatchMapper;
import cn.edu.hhstu.entity.Excel.DeviceBatchExcel;
import cn.edu.hhstu.pojo.DeviceBatch;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  DeviceBatchService implements IDeviceBatchService {

    @Autowired
    private IDeviceBatchMapper deviceBatchMapper;

    @Override
    public List<DeviceBatch> list(DeviceBatch entity, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return deviceBatchMapper.list(entity);
    }

    @Override
    public List<DeviceBatchExcel> listExcel(DeviceBatch entity) throws Exception {
        return deviceBatchMapper.listExcel(entity);
    }

    @Override
    public DeviceBatch detail(String id) throws Exception {
        return deviceBatchMapper.detail(id);
    }

    @Override
    public int insert(DeviceBatch entity) throws Exception {
        return deviceBatchMapper.insert(entity);
    }

    @Override
    public int update(DeviceBatch entity) throws Exception {
        return deviceBatchMapper.update(entity);
    }

    @Override
    public int delete(String id) throws Exception {
        return deviceBatchMapper.delete(id);
    }

    @Override
    public DeviceBatch lastBatchNo(String deviceType) throws Exception {
        return deviceBatchMapper.lastBatchNo(deviceType);
    }
}
