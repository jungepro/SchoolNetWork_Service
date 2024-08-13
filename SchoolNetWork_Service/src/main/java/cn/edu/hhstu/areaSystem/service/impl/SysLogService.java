package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.ISysLogMapper;
import cn.edu.hhstu.areaSystem.service.ISysLogService;
import cn.edu.hhstu.pojo.LogSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class  SysLogService implements ISysLogService {

    @Autowired
    private ISysLogMapper sysLogMapper;

    @Override
    public int insert(LogSys entity) throws Exception {
        return sysLogMapper.insert(entity);
    }
}
