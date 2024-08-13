package cn.edu.hhstu.security.service;

import cn.edu.hhstu.security.entity.SysMenuEntity;
import cn.edu.hhstu.security.pojo.SysMenu;

import java.util.List;

/**
 * @author wly
 * @version 1.0
 * @date 2021/1/18 17:36
 * @desc 无
 */
public interface ISysMenuService {

    public List<SysMenu> list() throws Exception;
    public SysMenu detail(long id) throws Exception;
    public int insert(SysMenu entity) throws Exception;
    public int update(SysMenu entity) throws Exception;
    public int delete(int id) throws Exception;
    public List<SysMenu> parentList(long parentId) throws Exception;
    public List<SysMenu> ownPermissions(Integer roleId) throws Exception;
    public List<SysMenu> noPermissions(Integer roleId) throws Exception;
    public List<SysMenuEntity> listByUserId(String id) throws Exception;
    public List<SysMenuEntity> listByAll() throws Exception;
}
