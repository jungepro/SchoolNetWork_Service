package cn.edu.hhstu.areaApplication.service;

import cn.edu.hhstu.entity.ApplicationSelfEntity;
import cn.edu.hhstu.entity.DeviceEntity;
import cn.edu.hhstu.entity.Excel.ApplicationExcel;
import cn.edu.hhstu.entity.Excel.ApplicationSelfExcel;
import cn.edu.hhstu.pojo.Application;
import cn.edu.hhstu.pojo.ApplicationSelf;

import java.util.HashMap;
import java.util.List;


public interface IApplicationSelfService {
    public List<ApplicationSelfEntity> listPage(HashMap params, Integer page, Integer rows) throws Exception;
    public List<ApplicationSelfExcel> listExcel(HashMap params) throws Exception;

    public ApplicationSelfEntity detail(String id) throws Exception;

    public int insert(ApplicationSelf entity) throws Exception;

    public int update(ApplicationSelf entity) throws Exception;

    public int delete(String id) throws Exception;

}
