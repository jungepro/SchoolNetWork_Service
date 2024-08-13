package cn.edu.hhstu.security.entity;

import cn.edu.hhstu.security.pojo.SysMenu;

import java.util.List;

public class SysMenuEntity extends SysMenu {
    private boolean own;    //角色是否已拥有权限

    private List<SysMenuEntity> children;

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }

    public List<SysMenuEntity> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenuEntity> children) {
        this.children = children;
    }
}
