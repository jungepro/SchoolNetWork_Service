package cn.edu.hhstu.utils;

import cn.edu.hhstu.security.entity.UserDetailsEntity;
import cn.edu.hhstu.security.pojo.LoginUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class  UserUtil {

    //获取登录用户实体类
    public static UserDetailsEntity getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return null;
            }
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return (UserDetailsEntity) authentication.getPrincipal();
            }
        }
        return null;
    }

    //获取登录用户名
    public static String getLoginUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return "anonymous";
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return ((UserDetailsEntity) authentication.getPrincipal()).getUsername();
            }
        }
        return "anonymous";
    }

    //获取登录用户姓名
    public static String getLoginRealName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return "anonymous";
            }

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                return ((UserDetailsEntity) authentication.getPrincipal()).getRealName();
            }
        }
        return "anonymous";
    }
}
