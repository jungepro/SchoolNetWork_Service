package cn.edu.hhstu.areaHome.service;

import cn.edu.hhstu.security.pojo.LoginUser;


public interface  IIndexService {
    public int serverCount(int deviceTypeId) throws Exception;
    public int serverTrustCount(String departmentId) throws Exception;
    public int vmServerCount() throws Exception;
    public int applicationCount() throws Exception;
    public int applicationCount(String departmentId) throws Exception;
    public int operationLogCount() throws Exception;

    public LoginUser userDetail(String id) throws Exception;
    public int passwordSave(String userId,String pwd) throws Exception;
}
