package com.zxy.config;

import com.zxy.utils.FileTypeUtils;
import com.zxy.utils.MailTask;
import com.zxy.utils.Md5Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //设置文件虚拟路径映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/filePan/avatar/**").addResourceLocations("file:D:\\filePan\\avatar\\");
        registry.addResourceHandler("/filePan/file/**").addResourceLocations("file:D:\\filePan\\files\\");
    }

    //设置视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //访问首页跳转
        registry.addViewController("/").setViewName("loginandreg");
        registry.addViewController("/loginandreg.html").setViewName("loginandreg");
    }
    //拦截器配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置公开的资源
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/css/**","/js/**","/img/**","/icon/**","/layui/**",
                        "/loginandreg.html","/loginandreg","loginandreg","error/404.html","/user/loginandreg",
                        "/user/login","/user/getmailcode","/user/touristlogin","/user/yklogin");
    }


    //将使用的工具类注册到容器中（也可通过注解注册）
    @Bean
    public MailTask getMailCode(){
        return new MailTask();
    }
    @Bean
    public Md5Utils md5Utils(){
        return new Md5Utils();
    }
    @Bean
    public FileTypeUtils fileTypeUtils(){
        return new FileTypeUtils();
    }

}
