package cn.edu.hhstu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @desc 根据判断yml是否是开发环境配置自动加载，扫描的类中@Api开头的注解是swagger-ui.html页面的描述信息
 */
@Configuration
@EnableSwagger2
public class  SwaggerConfig {

    //yml配置注解
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.version}")
    private String version;

    @Bean
    public Docket docket(Environment environment){

        //判断环境，设置swagger仅开发环境显示
        Profiles profiles = Profiles.of("dev", "test");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("默认库")
                .enable(flag);
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("cn.edu.hhstu.areaHome.controller"))
//                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("王凌云", "", "wly@hhstu.edu.cn");
//        return new ApiInfo(
//                title,
//                "接口文档",
//                "v1.0",
//                "http://www.hhstu.edu.cn",
//                contact,
//                "Apache 2.0",
//                "http://www.apache.org/licenses/LICENSE-2.0",
//                new ArrayList()
//        );
        return new ApiInfoBuilder()
                .title(title)
//                .description("接口文档")
                .contact(contact)
                .version(version)
                .build();
    }
}
