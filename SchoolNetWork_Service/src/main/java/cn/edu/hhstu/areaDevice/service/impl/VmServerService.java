package cn.edu.hhstu.areaDevice.service.impl;

import cn.edu.hhstu.areaDevice.service.IVmServerService;
import cn.edu.hhstu.areaDevice.mapper.IVmServerMapper;
import cn.edu.hhstu.entity.Excel.VmServerExcel;
import cn.edu.hhstu.pojo.VmServer;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class  VmServerService implements IVmServerService {

    @Autowired
    private IVmServerMapper vmServerMapper;

    @Override
    public List<VmServer> list(HashMap params, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return vmServerMapper.list(params);
    }

    @Override
    public List<VmServerExcel> listExcel(HashMap params) throws Exception {
        return vmServerMapper.listExcel(params);
    }

    @Override
    public VmServer detail(String id) throws Exception {
        return vmServerMapper.detail(id);
    }

    @Override
    public int insert(VmServer entity) throws Exception {
        return vmServerMapper.insert(entity);
    }

    @Override
    public int update(VmServer entity) throws Exception {
        return vmServerMapper.update(entity);
    }

    @Override
    public String lastDeviceNo() throws Exception {
        String deviceNo = "";
        VmServer vmServer = vmServerMapper.lastDeviceNo();
        if(vmServer!=null) {
            deviceNo = vmServer.getServerNo();
        }
        return deviceNo;
    }

    @Override
    public int haveApplication(String deviceId) throws Exception {
        return vmServerMapper.haveApplication(deviceId);
    }

    @Override
    public int offline(String id) throws Exception {
        return vmServerMapper.offline(id);
    }

    @Override
    public int delete(String id) throws Exception {
        return vmServerMapper.delete(id);
    }
}
