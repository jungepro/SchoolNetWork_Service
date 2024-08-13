package cn.edu.hhstu.config;

import cn.edu.hhstu.areaSystem.service.ISysLogService;
import cn.edu.hhstu.pojo.LogSys;
import cn.edu.hhstu.security.entity.UserDetailsEntity;
import cn.edu.hhstu.utils.UserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
@Aspect
public class  LogAop {
    private Date visitTime;
    private String ip;
    private String url;
    private String requestMethod;
    private String method;

    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("execution(* cn.edu.hhstu.*.controller.*.*(..))")//切入点描述
    public void controllerLog(){}

    @Pointcut("execution(* cn.edu.hhstu.areaHome.controller.IndexController.*(..))")//切入点描述
    public void homeControllerLog(){}

    //aop前置通知，主要获取开始时间，执行的类名和方法名
    //@Before("controllerLog() || homeControllerLog()")
    @Before("execution(* cn.edu.hhstu.*.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();             //访问开始时间

    }

    //aop后置通知
    @After("execution(* cn.edu.hhstu.*.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime(); //访问时长
        Class<?> clazz = jp.getTarget().getClass();

        if(clazz != null && clazz != LogAop.class) {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //获取request对象
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            //获取session对象
            //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
            ip = request.getRemoteAddr();
            url = request.getRequestURL().toString();
            requestMethod = request.getMethod();
            method = jp.getSignature().getDeclaringTypeName() + "." + jp.getSignature().getName();
//            String args = Arrays.toString(jp.getArgs());

            //保存日志数据
            LogSys logSys = new LogSys();
            logSys.setExecutionTime(time);
            logSys.setIp(ip);
            logSys.setUrl(url);
            logSys.setRequestMethod(requestMethod);
            logSys.setUserName(UserUtil.getLoginUserName());
            logSys.setMethod(method);
            logSys.setVisitTime(visitTime);

            sysLogService.insert(logSys);
        }
    }
}
