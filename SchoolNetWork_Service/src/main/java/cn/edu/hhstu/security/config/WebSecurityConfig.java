package cn.edu.hhstu.security.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.web.client.RestTemplate;
//
//@Configuration
//@EnableOAuth2Sso
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(
//                "/error/**","/code","/css/**","/js/**","/plugins/**","/images/**","/druid/**","/swagger**","/swagger**/**","doc.html");
//    }
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//                http.csrf().disable()                           //关闭csrf防护
//                .headers().frameOptions().disable()     //开启iframe嵌入网页
//                .and();
//        http
//                .authorizeRequests()
//                .antMatchers("/login**","/sign").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/logout")
//                .deleteCookies("OAUTH2SESSION");
//    }
//}

import cn.edu.hhstu.security.handler.MyAuthenticationFailureHandler;
import cn.edu.hhstu.security.handler.MyAuthenticationSuccessHandler;
import cn.edu.hhstu.security.handler.MyLogoutSuccessHandler;
import cn.edu.hhstu.security.handler.VerifyCodeFilter;
import cn.edu.hhstu.security.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    VerifyCodeFilter verifyCodeFilter;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private MyUserDetailsService userService;

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);     //登录验证码

        http.csrf().disable()                           //关闭csrf防护
                .headers().frameOptions().disable()     //开启iframe嵌入网页
                .and();

        http.formLogin()
                .loginPage("/login")
                .usernameParameter("u")
                .passwordParameter("p")
                .loginProcessingUrl("/loginValid")    //登陆提交地址
//                .defaultSuccessUrl("/index")    //登陆成功后跳转页面
//                .failureUrl("/loginError")    //登陆失败后跳转页面
                .permitAll()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
//                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and();

        http.authorizeRequests()    //授权配置
//                .antMatchers("/**").permitAll()
                .antMatchers("/error/**","/code").permitAll()
                .antMatchers("/css/**","/js/**","/plugins/**","/images/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/swagger**","/swagger**/**","doc.html").permitAll()
                .anyRequest().authenticated()
                .and();

//        http.exceptionHandling().accessDeniedPage("/error/403.html"); //自定义无权限访问页面
    }

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
        //开启内存认证
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("wly")		   //用户名
//                .password(new BCryptPasswordEncoder().encode("111")) //密码
//                .roles("teacher")		//用户角色名
//                .and()
//                .withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("111"))
//                .roles("admin");
    }
}
