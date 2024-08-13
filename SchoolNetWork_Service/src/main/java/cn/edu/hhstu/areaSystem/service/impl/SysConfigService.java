package cn.edu.hhstu.areaSystem.service.impl;

import cn.edu.hhstu.areaSystem.mapper.ISysConfigMapper;
import cn.edu.hhstu.areaSystem.service.ISysConfigService;
import cn.edu.hhstu.entity.AppAuditConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wly
 * @version 1.0
 * @date 2021/3/16 11:13
 * @desc 无
 */
@Service
public class  SysConfigService implements ISysConfigService {

    @Autowired
    private ISysConfigMapper sysConfigMapper;

    @Override
    public AppAuditConfigEntity getAppAuditConfig() throws Exception {
        return sysConfigMapper.getAppAuditConfig();
    }

    @Override
    public int setAppAuditConfig(AppAuditConfigEntity entity) throws Exception {
        return sysConfigMapper.setAppAuditConfig(entity);
    }
}
