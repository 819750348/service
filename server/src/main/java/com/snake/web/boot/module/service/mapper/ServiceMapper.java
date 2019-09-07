package com.snake.web.boot.module.service.mapper;

import com.snake.web.boot.module.service.model.StatsBean;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface ServiceMapper extends Mapper<StatsBean>, MySqlMapper<StatsBean> {
}
