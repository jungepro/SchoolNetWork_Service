package cn.edu.hhstu.areaApplication.service.impl;

import cn.edu.hhstu.areaApplication.mapper.IApplicationAuditMapper;
import cn.edu.hhstu.areaApplication.service.IApplicationAuditService;
import cn.edu.hhstu.entity.Excel.ApplicationAuditExcel;
import cn.edu.hhstu.pojo.ApplicationAudit;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@Service
public class  ApplicationAuditService implements IApplicationAuditService {

    @Autowired
    private IApplicationAuditMapper applicationAuditMapper;

    @Override
    public List<ApplicationAudit> listPage(HashMap params, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return applicationAuditMapper.list(params);
    }

    @Override
    public List<ApplicationAuditExcel> listExcel(HashMap params) throws Exception {
        return applicationAuditMapper.listExcel(params);
    }

    @Override
    public ApplicationAudit detail(String id) throws Exception {
        return applicationAuditMapper.detail(id);
    }

    @Override
    public int update(ApplicationAudit entity) throws Exception {
        return applicationAuditMapper.update(entity);
    }

    @Override
    public int delete(String id) throws Exception {
        return applicationAuditMapper.delete(id);
    }

    @Override
    public int born(int annual) throws Exception {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("annual",annual);
        applicationAuditMapper.born(map);
        return Integer.parseInt(map.get("rows").toString());
    }

    @Override
    public int updateAudit(ApplicationAudit entity) throws Exception {
        return applicationAuditMapper.updateAudit(entity);
    }
}
