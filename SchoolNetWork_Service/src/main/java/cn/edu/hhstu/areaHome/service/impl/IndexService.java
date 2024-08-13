package cn.edu.hhstu.areaHome.service.impl;

import cn.edu.hhstu.areaHome.mapper.IIndexMapper;
import cn.edu.hhstu.areaHome.service.IIndexService;
import cn.edu.hhstu.security.pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class IndexService implements IIndexService {

    @Autowired
    private IIndexMapper indexMapper;

    @Override
    public int serverCount(int deviceTypeId) throws Exception {
        return indexMapper.serverCount(deviceTypeId);
    }

    @Override
    public int serverTrustCount(String departmentId) throws Exception {
        return indexMapper.serverTrustCount(departmentId);
    }

    @Override
    public int vmServerCount() throws Exception {
        return indexMapper.vmServerCount();
    }

    @Override
    public int applicationCount() throws Exception {
        return indexMapper.applicationCount();
    }

    @Override
    public int applicationCount(String departmentId) throws Exception {
        return indexMapper.applicationDepartmentCount(departmentId);
    }

    @Override
    public int operationLogCount() throws Exception {
        return indexMapper.operationLogCount();
    }

    @Override
    public LoginUser userDetail(String id) throws Exception {
        return indexMapper.userDetail(id);
    }

    @Override
    public int passwordSave(String userId, String pwd) throws Exception {
        return indexMapper.passwordSave(userId,pwd);
    }
}
