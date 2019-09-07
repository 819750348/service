package com.snake.web.boot.module.rup.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model/")
public class ModelPvController {


    /**
     *实现模型的预览量，同事更新模型表pv值加1
     * @param modelId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "modelPv")
    Object modelPv(Long modelId){

        return  null ;
    }

}
