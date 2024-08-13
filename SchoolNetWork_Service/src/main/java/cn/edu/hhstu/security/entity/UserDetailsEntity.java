package cn.edu.hhstu.security.entity;

import cn.edu.hhstu.security.pojo.LoginRole;
import cn.edu.hhstu.security.pojo.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsEntity extends LoginUser implements UserDetails {

  private List<SysMenuEntity> permissions;

  public List<SysMenuEntity> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<SysMenuEntity> permissions) {
    this.permissions = permissions;
  }

  //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (SysMenuEntity permission : permissions) {
      //将可用的按钮类（2）权限加入，左侧列表类（1）在登录后动态生成，这里不做加入
      if(permission.getType() == 2 && StringUtils.isNotEmpty(permission.getPermission()) && !permission.isDisabled())
        authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
    }
    return authorities;
  }

    @Override
    public String getPassword() {
        return super.getPwd();
    }

    @Override
    public String getUsername() {
        return super.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !super.getDisabled();
    }
}
