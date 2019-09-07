//package com.snake.web.boot.module.interfaces.exception;
//
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice(
//        basePackages = {"com.snake.web.boot.module.interfaces.*"},
//        annotations = {Controller.class,RestController.class}
//
//)
//public class ServiceControllerAdvice  {
// @ExceptionHandler(value = NotFoundException.class)
// @ResponseBody
// @ResponseStatus(HttpStatus.BAD_REQUEST)
// public Map exception(){
//     Map map =new HashMap<Object,Object>();
//     map.put("error_code","1");
//     map.put("error_description","");
//     return map;
// }
//}
