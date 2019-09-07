package com.snake.web.boot.module.interfaces.service;

import com.snake.web.boot.module.interfaces.mapper.ServiceRESTfulMapper;
import com.snake.web.boot.module.interfaces.model.RestfulBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRESTfulService {
@Autowired
    ServiceRESTfulMapper serviceRESTfulMapper;
    public List<RestfulBean> allservice(){
        List<RestfulBean> list=serviceRESTfulMapper.selectAll();
        return list;
    }
}
