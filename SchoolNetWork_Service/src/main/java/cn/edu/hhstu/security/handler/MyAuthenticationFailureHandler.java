package cn.edu.hhstu.security.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class  MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code",400);
        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            map.put("msg","用户名或密码错误！");
        } else if (e instanceof LockedException) {
            map.put("msg","用账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            map.put("msg","密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            map.put("msg","账号过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            map.put("msg","账户被禁用，请联系管理员!");
        } else {
            map.put("msg",e.getMessage()+"，登录失败!");
        }

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
