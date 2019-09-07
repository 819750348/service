package com.snake.web.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * Created by HP on 2018/7/2.
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.snake.web.boot.module.servlet")
@MapperScan({"com.snake.web.boot.module.*.mapper"})
public class Application  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
