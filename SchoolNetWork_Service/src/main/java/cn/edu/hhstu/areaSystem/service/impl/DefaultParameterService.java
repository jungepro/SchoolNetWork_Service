package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.IDefaultParameterMapper;
import cn.edu.hhstu.areaSystem.service.IDefaultParameterService;
import cn.edu.hhstu.pojo.DefaultParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class  DefaultParameterService implements IDefaultParameterService {

    @Autowired
    private IDefaultParameterMapper defaultParameterMapper;

    @Override
    public List<DefaultParameter> unuseList(Integer deviceTypeId,String deviceId) throws Exception {
        return defaultParameterMapper.unuseList(deviceTypeId,deviceId);
    }

    @Override
    public List<DefaultParameter> list() throws Exception {
        return defaultParameterMapper.list();
    }
}
