package com.snake.web.boot.module.rup.controller;

import com.snake.web.boot.config.ApiResult;
import com.snake.web.boot.config.ApiResultType;
import com.snake.web.boot.module.rup.model.BasicParam;
import com.snake.web.boot.module.rup.repository.BasicParamRepository;
import com.snake.web.boot.module.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basicParam/")
public class BasicParamController {

    @Autowired
    private BasicParamRepository basicParamRepository;

    /**
     * 保存
     *
     * @param basicParam
     * @return
     */
    @RequestMapping(value = "save")
    Object save(@RequestBody BasicParam basicParam) {
        basicParamRepository.save(basicParam);
        basicParamRepository.flush();
        return get();
    }


    /**
     * 获取
     * @return
     */
    @RequestMapping(value = "get")
    Object get() {
        BasicParam basicParam = new BasicParam();
        List<BasicParam> list = basicParamRepository.findAll();
        if (list.size() > 0) {
            basicParam = list.get(0);
        }
        return basicParam;
    }

}
