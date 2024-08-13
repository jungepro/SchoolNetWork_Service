package cn.edu.hhstu;

import cn.jasonone.ueditor.EnableUeditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//@EnableUeditor //启用Ueditor
//public class OperationApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(OperationApplication.class, args);
//    }
//
//}
//部署成war包时替换如下
@SpringBootApplication
@EnableUeditor //启用Ueditor
public class OperationApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OperationApplication.class, args);
    }

    //外部tomcat启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OperationApplication.class);
    }
}
