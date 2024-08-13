package cn.edu.hhstu.security.service.impl;

import cn.edu.hhstu.security.mapper.ILoginRoleMapper;
import cn.edu.hhstu.security.pojo.LoginRole;
import cn.edu.hhstu.security.service.ILoginRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wly
 * @version 1.0
 * @date 2021/1/12 8:07
 * @desc 用户角色
 */
@Service
public class LoginRoleService implements ILoginRoleService {

    @Autowired
    private ILoginRoleMapper loginRoleMapper;

    @Override
    public List<LoginRole> list() throws Exception {
        return loginRoleMapper.list();
    }

    @Override
    public LoginRole detail(int id) throws Exception {
        return loginRoleMapper.detail(id);
    }

    @Override
    public int insert(LoginRole entity) throws Exception {
        return loginRoleMapper.insert(entity);
    }

    @Override
    public int update(LoginRole entity) throws Exception {
        return loginRoleMapper.update(entity);
    }

    @Override
    public int delete(int id) throws Exception {
        return loginRoleMapper.delete(id);
    }

    @Override
    public int existPermission(Integer roleId, Integer menuId) throws Exception {
        return loginRoleMapper.existPermission(roleId,menuId);
    }

    @Override
    public int insertPermission(Integer roleId, Integer menuId) throws Exception {
        return loginRoleMapper.insertPermission(roleId,menuId);
    }

    @Override
    public int insertAllPermissions(Integer roleId) throws Exception {
        return loginRoleMapper.insertAllPermissions(roleId);
    }

    @Override
    public int deletePermission(Integer roleId, Integer menuId) throws Exception {
        return loginRoleMapper.deletePermission(roleId,menuId);
    }

    @Override
    public int deletePermissions(Integer roleId, int[] menuIds) throws Exception {
        return loginRoleMapper.deletePermissions(roleId,menuIds);
    }

    @Override
    public int deleteAllPermissions(Integer roleId) throws Exception {
        return loginRoleMapper.deleteAllPermissions(roleId);
    }
}
