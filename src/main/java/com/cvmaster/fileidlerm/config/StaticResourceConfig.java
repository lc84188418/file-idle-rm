package com.cvmaster.fileidlerm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName:StaticResourceConfig
 * @Desc:TODO
 * @Author: wenchao
 * @CreateTime:2024/3/5 23:16
 * @Version:1.0
 **/
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/myStatic/**").addResourceLocations("file:E:/test/file_idle_rm/");
    }
}
