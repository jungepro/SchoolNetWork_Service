package cn.edu.hhstu.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;

import java.text.SimpleDateFormat;

/**
 * 自定义Web绑定初始化器
 *
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport 的getConfigurableWebBindingInitializer方法
 */

@Configuration
@ControllerAdvice
public class  WebBindingInitializerConfiguration {
//    final ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
//    final FormattingConversionService conversionService = new DefaultFormattingConversionService();

    //    @Bean
//    public ConfigurableWebBindingInitializer configurableWebBindingInitializer(FormattingConversionService conversionService, Validator mvcValidator) {
//        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
//        initializer.setConversionService(conversionService);
//        initializer.setValidator(mvcValidator);
//        //装配自定义属性编辑器
//        initializer.setPropertyEditorRegistrar(propertyEditorRegistry -> {
//            //PropertyEditors并不是线程安全的，对于每一个请求，我们都需要new一个PropertyEditor对象
//            propertyEditorRegistry.registerCustomEditor(String.class, new StringEscapeEditor());
//            propertyEditorRegistry.registerCustomEditor(Date.class, new DateEditor());
//        });
//        return initializer;
//    }
    @Bean
    public ConfigurableWebBindingInitializer getConfigurableWebBindingInitializer() {
        ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
        FormattingConversionService conversionService = new DefaultFormattingConversionService();
        //we can add our custom converters and formatters
        //conversionService.addConverter(...);
        //conversionService.addFormatter(...);
        initializer.setConversionService(conversionService);
        //we can set our custom validator
        //initializer.setValidator(....);

        //here we are setting a custom PropertyEditor
        initializer.setPropertyEditorRegistrar(propertyEditorRegistry -> {
//            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//            propertyEditorRegistry.registerCustomEditor(java.util.Date.class,
//                    new CustomDateEditor(dateFormatter, true));

            propertyEditorRegistry.registerCustomEditor(String.class, new StringEditor());
        });
        return initializer;
    }
}
