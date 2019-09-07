package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.rup.model.ServiceCategory;
import com.snake.web.boot.module.rup.model.ServiceInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface ServiceCategoryMapper extends Mapper<ServiceCategory>, MySqlMapper<ServiceCategory> {
}
