package cn.edu.hhstu.security.mapper;

import cn.edu.hhstu.security.pojo.LoginRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @author wly
 * @version 1.0
 * @date 2021/1/12 8:01
 * @desc 用户角色
 */
@Mapper
@Repository
public interface ILoginRoleMapper {

    @Select("select * from login_role")
    public List<LoginRole> list() throws Exception;

    //详细信息
    @Select("select * from login_role where roleId=#{id}")
    public LoginRole detail(int id) throws Exception;

    //新增保存
    @Insert("insert into login_role (roleid,roleName,remark) values (#{roleId},#{roleName},#{remark})")
    public int insert(LoginRole model) throws Exception;

    //更新保存
    @Update("update login_role set roleName=#{roleName},remark=#{remark} where roleId=#{roleId}")
    public int update(LoginRole model) throws Exception;

    //删除单条记录
    @Delete("delete from login_role where roleId=#{id}")
    public int delete(int id) throws Exception;

    //是否存在权限
    @Select("select count(0) from role_menu where roleId=#{roleId} and menuId=#{menuId}")
    public int existPermission(Integer roleId,Integer menuId) throws Exception;

    //添加保存单条权限
    @Insert("insert into role_menu (roleId,menuId) values (#{roleId},#{menuId})")
    public int insertPermission(Integer roleId,Integer menuId) throws Exception;

    //添加所有权限
    @Insert("insert into role_menu (roleId,menuId) select #{roleId},id from sys_menu where id not in (select menuId from role_menu where roleId=#{roleId})")
    public int insertAllPermissions(Integer roleId) throws Exception;

    //删除权限
    @Delete("delete from role_menu where roleId=#{roleId} and menuId=#{menuId}")
    public int deletePermission(Integer roleId,Integer menuId) throws Exception;

    //删除选择的权限
    @Delete({
            "<script>",
            "delete from role_menu where roleId=#{roleId} and  menuId in",
            "<foreach collection='menuIds' item='menuId' open='(' separator=',' close=')'>",
            "#{menuId}",
            "</foreach>",
            "</script>"
    })
    public int deletePermissions(Integer roleId,int[] menuIds) throws Exception;

    //删除角色所有权限
    @Delete("delete from role_menu where roleId=#{roleId}")
    public int deleteAllPermissions(Integer roleId) throws Exception;
}
