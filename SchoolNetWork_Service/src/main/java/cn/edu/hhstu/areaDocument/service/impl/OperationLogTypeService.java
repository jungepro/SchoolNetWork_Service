package cn.edu.hhstu.areaDocument.service.impl;

import cn.edu.hhstu.areaDocument.mapper.IOperationLogTypeMapper;
import cn.edu.hhstu.areaDocument.service.IOperationLogTypeService;
import cn.edu.hhstu.pojo.OperationLogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  OperationLogTypeService implements IOperationLogTypeService {

    @Autowired
    private IOperationLogTypeMapper operationLogTypeMapper;

    @Override
    public List<OperationLogType> list() throws Exception {
        return operationLogTypeMapper.list();
    }
}
