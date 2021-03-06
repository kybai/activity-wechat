package com.activity.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * Created by ky.bai on 2018-02-10 20:46
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private WechatInterceptor wechatInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("activity/index");
        registry.addViewController("/").setViewName("activity/index");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(wechatInterceptor).addPathPatterns("/wechat/**")
                .excludePathPatterns("/wechat/portal", "/wechat/portal/index", "/wechat/menu/**",
                        "/wechat/activity", "/wechat/activity/list", "/wechat/activity/info/**", "/wechat/activity/thumbup");
        super.addInterceptors(registry);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("20MB");
        return factory.createMultipartConfig();
    }

}
