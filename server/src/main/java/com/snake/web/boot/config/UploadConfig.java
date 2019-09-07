package com.snake.web.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by HP on 2018/11/16.
 */
//@Configuration
public class UploadConfig {

//    @Bean
    public CommonsMultipartResolver getCommonsMultipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(1024*1024*100);
        commonsMultipartResolver.setMaxInMemorySize(1024 * 4);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        commonsMultipartResolver.setResolveLazily(false);
        return commonsMultipartResolver;
    }

}
