package cn.edu.hhstu.areaDevice.service;

import cn.edu.hhstu.entity.Excel.DeviceBatchExcel;
import cn.edu.hhstu.pojo.DeviceBatch;

import java.util.List;

public interface  IDeviceBatchService {
    public List<DeviceBatch> list(DeviceBatch entity, Integer page, Integer rows) throws Exception;
    public List<DeviceBatchExcel> listExcel(DeviceBatch entity) throws Exception;

    public DeviceBatch detail(String id) throws Exception;

    public int insert(DeviceBatch entity) throws Exception;

    public int update(DeviceBatch entity) throws Exception;

    public int delete(String id) throws Exception;

    public DeviceBatch lastBatchNo(String deviceType) throws Exception;
}
