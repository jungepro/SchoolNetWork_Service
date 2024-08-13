package cn.edu.hhstu.areaSystem.service;

import cn.edu.hhstu.entity.AppAuditConfigEntity;

public interface  ISysConfigService {
    public AppAuditConfigEntity getAppAuditConfig() throws Exception;
    public int setAppAuditConfig(AppAuditConfigEntity entity) throws Exception;
}
