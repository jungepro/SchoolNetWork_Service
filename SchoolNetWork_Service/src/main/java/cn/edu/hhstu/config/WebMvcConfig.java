package cn.edu.hhstu.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Configuration
public class  WebMvcConfig implements WebMvcConfigurer {

    //配置跨域请求
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")  //设置允许跨域的路径
//                .allowedOrigins("*")//设置允许跨域请求的域名
//                .allowedMethods("GET","POST","DELETE","PUT")
//                .allowCredentials(true)//是否允许证书 不再默认开启
//                .allowedHeaders("*")
//                .maxAge(3600);
//    }

    //拦截器配置
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RequestParamInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/index","/error");
//    }

    //自定义资源映射
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
////        swagger buitiful ui地址
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index");
    }
}
