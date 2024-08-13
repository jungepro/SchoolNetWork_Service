package cn.edu.hhstu.areaDevice.service;

import cn.edu.hhstu.entity.Excel.VmServerExcel;
import cn.edu.hhstu.pojo.VmServer;

import java.util.HashMap;
import java.util.List;

public interface  IVmServerService {
    public List<VmServer> list(HashMap params, Integer page, Integer rows) throws Exception;
    public List<VmServerExcel> listExcel(HashMap params) throws Exception;

    public VmServer detail(String id) throws Exception;

    public int insert(VmServer entity) throws Exception;

    public int update(VmServer entity) throws Exception;

    public String lastDeviceNo() throws Exception;

    public int haveApplication(String deviceId) throws Exception;
    public int offline(String id) throws Exception;
    public int delete(String id) throws Exception;
}
