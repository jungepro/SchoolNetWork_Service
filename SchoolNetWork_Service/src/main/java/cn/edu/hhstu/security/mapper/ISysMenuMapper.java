package cn.edu.hhstu.security.mapper;

import cn.edu.hhstu.security.entity.SysMenuEntity;
import cn.edu.hhstu.security.pojo.SysMenu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wly
 * @version 1.0
 * @date 2021/1/18 8:35
 * @desc 无
 */
@Mapper
@Repository
public interface ISysMenuMapper {

    //列表查询
    @Select("select * from sys_menu order by sort")
    public List<SysMenu> list() throws Exception;

    //详细信息
    @Select("select * from sys_menu where id=#{id}")
    public SysMenu detail(long id) throws Exception;

    //新增保存
    @Insert("insert into sys_menu (parentId,name,css,href,type,permission,sort,hidden,disabled,createTime) " +
            "values (#{parentId},#{name},#{css},#{href},#{type},#{permission},#{sort},#{hidden},#{disabled},#{createTime})")
    public int insert(SysMenu entity) throws Exception;

    //更新保存
    @Update("update sys_menu set parentId=#{parentId},name=#{name},css=#{css},href=#{href},type=#{type},permission=#{permission}," +
            "sort=#{sort},hidden=#{hidden},disabled=#{disabled} where id=#{id}")
    public int update(SysMenu entity) throws Exception;

    //删除单条记录
    @Delete("delete from sys_menu where id=#{id}")
    public int delete(int id) throws Exception;

    //查询父级菜单
    @Select("select * from sys_menu where parentId = (SELECT parentId FROM `sys_menu` where id=#{parentId})")
    public List<SysMenu> parentList(long parentId) throws Exception;

    //角色拥有权限
    @Select("select * from sys_menu where id in (select menuId from role_menu where roleId=#{roleId}) order by sort")
    public List<SysMenu> ownPermissions(Integer roleId) throws Exception;

    //角色拥有权限
    @Select("select * from sys_menu where id not in (select menuId from role_menu where roleId=#{roleId}) order by sort")
    public List<SysMenu> noPermissions(Integer roleId) throws Exception;

    //查询用户权限菜单
    @Select("SELECT DISTINCT sm.* FROM `sys_menu` sm INNER JOIN role_menu rm " +
            "ON sm.id=rm.menuId INNER JOIN role_user ru ON ru.RoleId=rm.roleId " +
            "WHERE UserId=#{id} and disabled=0 ORDER BY sort")
    public List<SysMenuEntity> listByUserId(String id) throws Exception;

    //获取所有权限
    @Select("SELECT DISTINCT * FROM sys_menu ORDER BY sort")
    public List<SysMenuEntity> listByAll() throws Exception;
}
