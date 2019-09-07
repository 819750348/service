package com.snake.web.boot.module.service.service;

import com.snake.web.boot.module.rup.model.ServiceInfo;
import com.snake.web.boot.module.service.mapper.ServiceMapper;
import com.snake.web.boot.module.service.model.StatsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    ServiceMapper dashboardMapper;

    public Object service1(){
        Example example=new Example(ServiceInfo.class);
        List<StatsBean> list=dashboardMapper.selectByExample(example);
        int serviceCount=list.size();
        return serviceCount;
    }
}
