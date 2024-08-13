package cn.edu.hhstu.areaApplication.service;

import cn.edu.hhstu.entity.Excel.ApplicationAuditExcel;
import cn.edu.hhstu.pojo.ApplicationAudit;

import java.util.HashMap;
import java.util.List;

public interface  IApplicationAuditService {
    public List<ApplicationAudit> listPage(HashMap params, Integer page, Integer rows) throws Exception;
    public List<ApplicationAuditExcel> listExcel(HashMap params) throws Exception;

    public ApplicationAudit detail(String id) throws Exception;
    public int update(ApplicationAudit entity) throws Exception;
    public int delete(String id) throws Exception;

    public int born(int annual) throws Exception;
    public int updateAudit(ApplicationAudit entity) throws Exception;
}
