package cn.edu.hhstu.security.service.impl;

import cn.edu.hhstu.security.entity.SysMenuEntity;
import cn.edu.hhstu.security.entity.UserDetailsEntity;
import cn.edu.hhstu.security.mapper.ILoginUserMapper;
import cn.edu.hhstu.security.mapper.ISysMenuMapper;
import cn.edu.hhstu.security.pojo.LoginUser;
import cn.edu.hhstu.security.pojo.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    ILoginUserMapper userMapper;
    @Autowired
    ISysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetailsEntity user = null;
        try {
            user = userMapper.getUserByAccount(s);
            //数据库中明文密码加密，用户登录时密码md5加密
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            user.setPwd(encode.encode(DigestUtils.md5DigestAsHex(user.getPassword().getBytes())));
            //菜单权限
            List<SysMenuEntity> sysMenuEntities;
            if(s.toLowerCase().equals("system")){
                sysMenuEntities = sysMenuMapper.listByAll();
            }
            else{
                sysMenuEntities = sysMenuMapper.listByUserId(user.getUserId());
            }
            user.setPermissions(sysMenuEntities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return user;
    }
}
