package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.pojo.DefaultParameter;

import java.util.List;

public interface  IDefaultParameterService {
    public List<DefaultParameter> unuseList(Integer deviceTypeId,String deviceId) throws Exception;
    public List<DefaultParameter> list() throws Exception;
}
