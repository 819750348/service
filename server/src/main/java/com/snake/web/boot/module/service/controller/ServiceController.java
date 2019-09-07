package com.snake.web.boot.module.service.controller;

import com.snake.web.boot.module.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
public class ServiceController {
    @Autowired
    ServiceService serviceService;
    @GetMapping("service1")
    public Object service1(){
        return serviceService.service1();
    }

}
