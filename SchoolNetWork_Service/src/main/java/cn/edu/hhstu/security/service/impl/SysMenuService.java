package cn.edu.hhstu.security.service.impl;

import cn.edu.hhstu.security.entity.SysMenuEntity;
import cn.edu.hhstu.security.mapper.ISysMenuMapper;
import cn.edu.hhstu.security.pojo.SysMenu;
import cn.edu.hhstu.security.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wly
 * @version 1.0
 * @date 2021/1/18 17:35
 * @desc 无
 */
@Service
public class SysMenuService implements ISysMenuService {

    @Autowired
    ISysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> list() throws Exception {
        return sysMenuMapper.list();
    }

    @Override
    public SysMenu detail(long id) throws Exception {
        return sysMenuMapper.detail(id);
    }

    @Override
    public int insert(SysMenu entity) throws Exception {
        return sysMenuMapper.insert(entity);
    }

    @Override
    public int update(SysMenu entity) throws Exception {
        return sysMenuMapper.update(entity);
    }

    @Override
    public int delete(int id) throws Exception {
        return sysMenuMapper.delete(id);
    }

    @Override
    public List<SysMenu> parentList(long parentId) throws Exception {
        return sysMenuMapper.parentList(parentId);
    }

    @Override
    public List<SysMenu> ownPermissions(Integer roleId) throws Exception {
        return sysMenuMapper.ownPermissions(roleId);
    }

    @Override
    public List<SysMenu> noPermissions(Integer roleId) throws Exception {
        return sysMenuMapper.noPermissions(roleId);
    }

    @Override
    public List<SysMenuEntity> listByUserId(String id) throws Exception {
        return sysMenuMapper.listByUserId(id);
    }

    @Override
    public List<SysMenuEntity> listByAll() throws Exception {
        return sysMenuMapper.listByAll();
    }
}
