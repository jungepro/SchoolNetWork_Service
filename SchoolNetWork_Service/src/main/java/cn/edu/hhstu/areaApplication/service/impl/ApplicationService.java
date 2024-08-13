package cn.edu.hhstu.areaApplication.service.impl;

import cn.edu.hhstu.areaApplication.mapper.IApplicationMapper;
import cn.edu.hhstu.areaApplication.service.IApplicationService;
import cn.edu.hhstu.areaSystem.mapper.IApplicationTypeMapper;
import cn.edu.hhstu.areaSystem.service.IApplicationTypeService;
import cn.edu.hhstu.entity.DeviceEntity;
import cn.edu.hhstu.entity.Excel.ApplicationExcel;
import cn.edu.hhstu.pojo.Application;
import cn.edu.hhstu.pojo.ApplicationType;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class  ApplicationService implements IApplicationService {

    @Autowired
    private IApplicationMapper applicationMapper;

    @Override
    public List<Application> listPage(HashMap params, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return applicationMapper.list(params);
    }

    @Override
    public List<Application> listChildren(HashMap params) throws Exception {
        return applicationMapper.list(params);
    }

    @Override
    public List<Application> listForDevice(HashMap params) throws Exception {
        return applicationMapper.list(params);
    }

    @Override
    public List<ApplicationExcel> listExcel(HashMap params) throws Exception {
        return applicationMapper.listExcel(params);
    }

    @Override
    public Application detail(String id) throws Exception {
        return applicationMapper.detail(id);
    }

    @Override
    public int insert(Application entity) throws Exception {
        return applicationMapper.insert(entity);
    }

    @Override
    public int update(Application entity) throws Exception {
        return applicationMapper.update(entity);
    }

    @Override
    public int delete(String id) throws Exception {
        return applicationMapper.delete(id);
    }

    @Override
    public int offline(String id) throws Exception {
        return applicationMapper.offline(id);
    }

    @Override
    public int online(String id) throws Exception {
        return applicationMapper.online(id);
    }

    @Override
    public DeviceEntity deviceDetail(String id) throws Exception {
        return applicationMapper.deviceDetail(id);
    }

    @Override
    public List<DeviceEntity> deviceList(String serverName, Integer deviceTypeId) throws Exception {
        return applicationMapper.deviceList(serverName,deviceTypeId);
    }

    @Override
    public int alterDevice(String id, String deviceId) throws Exception {
        return applicationMapper.alterDevice(id,deviceId);
    }
}
