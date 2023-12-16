package com.demo.config;


import com.demo.interceptor.AuthenticationInterceptor;
import com.demo.util.StringToDateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor())
                .addPathPatterns("/**" )  // 设置需要拦截的路径
                .excludePathPatterns("/","/login","/register","/checkUsername","/toLogin","/toRegister","/static/**");  // 设置不需要拦截的路径
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter("yyyy-MM-dd"));
    }
}

