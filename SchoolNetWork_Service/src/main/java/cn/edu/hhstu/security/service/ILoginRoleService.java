package cn.edu.hhstu.security.service;

import cn.edu.hhstu.security.pojo.LoginRole;

import java.util.List;

/**
 * @author wly
 * @version 1.0
 * @date 2021/1/12 8:04
 * @desc 用户角色
 */
public interface ILoginRoleService {
    public List<LoginRole> list() throws Exception;

    public LoginRole detail(int id) throws Exception;

    public int insert(LoginRole entity) throws Exception;

    public int update(LoginRole entity) throws Exception;

    public int delete(int id) throws Exception;

    public int existPermission(Integer roleId,Integer menuId) throws Exception;

    public int insertPermission(Integer roleId,Integer menuId) throws Exception;
    public int insertAllPermissions(Integer roleId) throws Exception;

    public int deletePermission(Integer roleId,Integer menuId) throws Exception;
    public int deletePermissions(Integer roleId,int[] menuIds) throws Exception;
    public int deleteAllPermissions(Integer roleId) throws Exception;
}
