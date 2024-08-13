package cn.edu.hhstu.security.service;


import cn.edu.hhstu.security.pojo.LoginUser;

import java.util.HashMap;
import java.util.List;

public interface ILoginUserService {
    public List<LoginUser> list(HashMap params, Integer page, Integer rows) throws Exception;
    public LoginUser detail(String id) throws Exception;
    public int insert(LoginUser entity) throws Exception;
    public int update(LoginUser entity) throws Exception;
    public int delete(String id) throws Exception;
    public int savePwd(String id,String pwd) throws Exception;
    public int insertRole(String userId,Integer roleId) throws Exception;
    public int deleteRole(String userId,Integer roleId) throws Exception;
    public void loginSuccess(LoginUser entity);
}
