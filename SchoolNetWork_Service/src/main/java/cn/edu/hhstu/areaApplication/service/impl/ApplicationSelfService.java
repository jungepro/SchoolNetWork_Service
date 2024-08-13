package cn.edu.hhstu.areaApplication.service.impl;

import cn.edu.hhstu.areaApplication.mapper.IApplicationSelfMapper;
import cn.edu.hhstu.areaApplication.service.IApplicationSelfService;
import cn.edu.hhstu.entity.ApplicationSelfEntity;
import cn.edu.hhstu.entity.Excel.ApplicationExcel;
import cn.edu.hhstu.entity.Excel.ApplicationSelfExcel;
import cn.edu.hhstu.pojo.ApplicationSelf;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class  ApplicationSelfService implements IApplicationSelfService {

    @Autowired
    private IApplicationSelfMapper applicationSelfMapper;

    @Override
    public List<ApplicationSelfEntity> listPage(HashMap params, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return applicationSelfMapper.list(params);
    }

    @Override
    public List<ApplicationSelfExcel> listExcel(HashMap params) throws Exception {
        return applicationSelfMapper.listExcel(params);
    }

    @Override
    public ApplicationSelfEntity detail(String id) throws Exception {
        return applicationSelfMapper.detail(id);
    }

    @Override
    public int insert(ApplicationSelf entity) throws Exception {
        return applicationSelfMapper.insert(entity);
    }

    @Override
    public int update(ApplicationSelf entity) throws Exception {
        return applicationSelfMapper.update(entity);
    }

    @Override
    public int delete(String id) throws Exception {
        return applicationSelfMapper.delete(id);
    }
}
