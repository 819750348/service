package com.snake.web.boot.config;

import com.snake.web.boot.module.interfaces.exception.NotFoundException;
import com.snake.web.boot.utils.HashMaps;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by HP on 2018/10/18.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public Object handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
//        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//        e.printStackTrace();//todo
//        return HashMaps.build().as("message", ExceptionUtils.getRootCauseMessage(e)).as("details", ExceptionUtils.getStackTrace(e));
//    }
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public Object notFoundException(HttpServletRequest request, HttpServletResponse response, Exception e){

       response.setStatus(HttpServletResponse.SC_NOT_FOUND);
       return "dddddd";
    }

}
