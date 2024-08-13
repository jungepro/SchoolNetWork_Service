package cn.edu.hhstu.areaApplication.service;

import cn.edu.hhstu.entity.DeviceEntity;
import cn.edu.hhstu.entity.Excel.ApplicationExcel;
import cn.edu.hhstu.pojo.Application;

import java.util.HashMap;
import java.util.List;

public interface  IApplicationService {
    public List<Application> listPage(HashMap params, Integer page, Integer rows) throws Exception;
    public List<Application> listChildren(HashMap params) throws Exception;
    public List<Application> listForDevice(HashMap params) throws Exception;
    public List<ApplicationExcel> listExcel(HashMap params) throws Exception;

    public Application detail(String id) throws Exception;

    public int insert(Application entity) throws Exception;

    public int update(Application entity) throws Exception;

    public int delete(String id) throws Exception;

    public int offline(String id) throws Exception;
    public int online(String id) throws Exception;

    public DeviceEntity deviceDetail(String id) throws Exception;

    public List<DeviceEntity> deviceList(String serverName,Integer deviceTypeId) throws Exception;
    public int alterDevice(String id, String deviceId) throws Exception;
}
