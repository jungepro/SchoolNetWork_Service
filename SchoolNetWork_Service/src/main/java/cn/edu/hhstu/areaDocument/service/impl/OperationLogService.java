package cn.edu.hhstu.areaDocument.service.impl;

import cn.edu.hhstu.areaDocument.mapper.IOperationLogMapper;
import cn.edu.hhstu.areaDocument.service.IOperationLogService;
import cn.edu.hhstu.pojo.OperationLog;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  OperationLogService implements IOperationLogService {

    @Autowired
    private IOperationLogMapper operationLogMapper;

    @Override
    public List<OperationLog> listPage(String operator, String deviceName, Integer operationLogTypeId, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return operationLogMapper.list(operator,deviceName,operationLogTypeId);
    }

    @Override
    public List<OperationLog> listByDevice(String deviceId) throws Exception {
        return operationLogMapper.listByDevice(deviceId);
    }

    @Override
    public OperationLog detail(String id) throws Exception {
        return operationLogMapper.detail(id);
    }

    @Override
    public int insert(OperationLog entity) throws Exception {
        return operationLogMapper.insert(entity);
    }

    @Override
    public int update(OperationLog entity) throws Exception {
        return operationLogMapper.update(entity);
    }

    @Override
    public int delete(String id) throws Exception {
        return operationLogMapper.delete(id);
    }
}
