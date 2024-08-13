package cn.edu.hhstu.areaDevice.service;

import cn.edu.hhstu.entity.Excel.DeviceExcel;
import cn.edu.hhstu.pojo.Device;

import java.util.HashMap;
import java.util.List;

public interface  IDeviceService {

    public List<Device> list(HashMap params, Integer page, Integer rows) throws Exception;
    public List<DeviceExcel> listExcel(HashMap params) throws Exception;

    public Device detail(String id) throws Exception;

    public int insert(Device entity) throws Exception;

    public int update(Device entity) throws Exception;

    public String lastDeviceNo(String deviceBatchId) throws Exception;
}
