package com.snake.web.boot.module.rup.controller;

import com.snake.web.boot.module.rup.model.ModelInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/model/")
public class ModelDownController {
    /**
     *实现模型的下载量，模型下载成功后记录，同事更新模型表download_count值加1
     * @param modelId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addModelDowns")
    Object addModelDowns(Long modelId){

        return  null ;
    }
    
}
