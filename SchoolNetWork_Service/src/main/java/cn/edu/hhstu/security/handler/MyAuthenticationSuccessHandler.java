package cn.edu.hhstu.security.handler;

import cn.edu.hhstu.security.pojo.LoginUser;
import cn.edu.hhstu.security.service.ILoginUserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class  MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ILoginUserService loginUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","登录成功");

        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("checkcode");   //移除验证码session

        LoginUser loginUser = new LoginUser();
        loginUser.setLastLoginIp(httpServletRequest.getRemoteAddr());
        loginUser.setLastLoginTime(new Timestamp(new Date().getTime()));
        String userName = authentication.getName().toString();
        loginUser.setAccount(userName);

        loginUserService.loginSuccess(loginUser);

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JSON.toJSONString(map));
        out.flush();
        out.close();
    }
}
