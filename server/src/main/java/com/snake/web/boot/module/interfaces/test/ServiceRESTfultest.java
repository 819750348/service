//package com.snake.web.boot.module.interfaces.test;
//
//import com.alibaba.druid.sql.visitor.functions.Char;
//import com.snake.web.boot.module.interfaces.model.RestfulBean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@ResponseBody
//@RequestMapping("/v1/")
//public class ServiceRESTfultest {
//    @RequestMapping("test")
//    public Object n() {
//        RestTemplate restTemplate=new RestTemplate();
////        ResponseEntity<RestfulBean> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/all/service",RestfulBean.class);
////        RestfulBean restfulBean = restTemplate.getForObject("http://127.0.0.1:8080/all/service",RestfulBean.class);
////        String restfulString = restTemplate.getForObject("http://127.0.0.1:8080/v1/string",String.class);
////        Object restfulOb = restTemplate.getForObject("http://127.0.0.1:8080/v1/ob",Object.class);
////        Object restfullist = restTemplate.getForObject("http://127.0.0.1:8080/v1/list",Object.class);
////
////
////
////
////        Object restfulservice = restTemplate.getForObject("http://127.0.0.1:8080/v1/service",Object.class);
//        ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://127.0.0.1:8080/v1/service",Object.class);
//
//        return  responseEntity;
//
//    }
//}
