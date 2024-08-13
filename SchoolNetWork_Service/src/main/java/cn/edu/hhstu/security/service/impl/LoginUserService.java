package cn.edu.hhstu.security.service.impl;

import cn.edu.hhstu.security.mapper.ILoginUserMapper;
import cn.edu.hhstu.security.pojo.LoginUser;
import cn.edu.hhstu.security.service.ILoginUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LoginUserService implements ILoginUserService {
    @Autowired
    private ILoginUserMapper loginUserMapper;

    @Override
    public List<LoginUser> list(HashMap params, Integer page, Integer rows) throws Exception {
        PageHelper.startPage(page, rows);
        return loginUserMapper.list(params);
    }

    @Override
    public LoginUser detail(String id) throws Exception {
        return loginUserMapper.detail(id);
    }

    @Override
    public int insert(LoginUser entity) throws Exception {
        return loginUserMapper.insert(entity);
    }

    @Override
    public int update(LoginUser entity) throws Exception {
        return loginUserMapper.update(entity);
    }

    @Override
    public int delete(String id) throws Exception {
        return loginUserMapper.delete(id);
    }

    @Override
    public int savePwd(String id,String pwd) throws Exception {
        return loginUserMapper.savePwd(id,pwd);
    }

    @Override
    public int insertRole(String userId, Integer roleId) throws Exception {
        return loginUserMapper.insertRole(userId,roleId);
    }

    @Override
    public int deleteRole(String userId, Integer roleId) throws Exception {
        return loginUserMapper.deleteRole(userId,roleId);
    }

    @Override
    public void loginSuccess(LoginUser entity) {
        loginUserMapper.loginSuccess(entity);
    }
}
