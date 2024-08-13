package cn.edu.hhstu.security.pojo;


import cn.edu.hhstu.pojo.Department;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LoginUser {// implements UserDetails

  private String userId;
  private String account;
  private String pwd;
  private String realName;
  private String phone;
  private String email;
  private long loginTimes;
  private String lastLoginIp;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private java.sql.Timestamp lastLoginTime;
  private boolean disabled;
  private boolean ssoLogin;
  private String departmentId;

  private Department department;
  private List<LoginRole> loginRoles;


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public long getLoginTimes() {
    return loginTimes;
  }

  public void setLoginTimes(long loginTimes) {
    this.loginTimes = loginTimes;
  }


  public String getLastLoginIp() {
    return lastLoginIp;
  }

  public void setLastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
  }


  public java.sql.Timestamp getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }


  public boolean getDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public boolean getSsoLogin() {
    return ssoLogin;
  }

  public void setSsoLogin(boolean ssoLogin) {
    this.ssoLogin = ssoLogin;
  }


  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public List<LoginRole> getLoginRoles() {
    return loginRoles;
  }

  public void setLoginRoles(List<LoginRole> loginRoles) {
    this.loginRoles = loginRoles;
  }


//  @Override
//  public Collection<? extends GrantedAuthority> getAuthorities() {
//    return null;
//  }
////  @Override
////  public Collection<? extends GrantedAuthority> getAuthorities() {
////    List<GrantedAuthority> authorities = new ArrayList<>();
////    for (LoginRole role : loginRoles) {
////      authorities.add(new SimpleGrantedAuthority(role.getRoleName()));//"ROLE_" +
////    }
////    return authorities;
////  }
//
//  @Override
//  public String getPassword() {
//    return pwd;
//  }
//
//  @Override
//  public String getUsername() {
//    return account;
//  }
//
//  //账号是否过期
//  @Override
//  public boolean isAccountNonExpired() {
//    return true;
//  }
//
//  //账号是否被冻结
//  @Override
//  public boolean isAccountNonLocked() {
//    return true;
//  }
//
//  //账号密码是否过期，定期重置密码时
//  @Override
//  public boolean isCredentialsNonExpired() {
//    return true;
//  }
//
//  //账号是否可用
//  @Override
//  public boolean isEnabled() {
//    return !disabled;
//  }
}
