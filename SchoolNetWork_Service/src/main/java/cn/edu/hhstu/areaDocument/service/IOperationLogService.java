package cn.edu.hhstu.areaDocument.service;

import cn.edu.hhstu.pojo.OperationLog;

import java.util.List;

public interface IOperationLogService {
    public List<OperationLog> listPage(String operator, String deviceName, Integer operationLogTypeId, Integer page, Integer rows) throws Exception;

    public List<OperationLog> listByDevice(String deviceId) throws Exception;

    public OperationLog detail(String id) throws Exception;
    public int insert(OperationLog entity) throws Exception;
    public int update(OperationLog entity) throws Exception;
    public int delete(String id) throws Exception;
}
