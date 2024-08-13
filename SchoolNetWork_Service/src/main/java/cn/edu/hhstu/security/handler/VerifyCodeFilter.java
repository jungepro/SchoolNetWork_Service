package cn.edu.hhstu.security.handler;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//可继承OncePerRequestFilter
@Component
public class  VerifyCodeFilter extends GenericFilterBean {
    private String defaultFilterProcessUrl = "/loginValid";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (defaultFilterProcessUrl.equals(request.getServletPath()) && "POST".equalsIgnoreCase(request.getMethod())) {
            // 验证码验证
            String requestCaptcha = request.getParameter("code");
            String genCaptcha = (String)request.getSession().getAttribute("checkcode");
            try {
                if (StringUtils.isEmpty(requestCaptcha)) {
                    throw new AuthenticationServiceException("验证码不能为空!");
                }
                if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                    throw new AuthenticationServiceException("验证码错误!");
                }
            }
            catch (AuthenticationServiceException e) {
                Map<String, Object> map = new HashMap<>();
                map.put("code",400);
                map.put("msg",e.getMessage());
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(map));
                out.flush();
                out.close();
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
