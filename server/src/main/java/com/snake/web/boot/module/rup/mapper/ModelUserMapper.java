package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.rup.model.ModelUser;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface ModelUserMapper extends Mapper<ModelUser>, MySqlMapper<ModelUser> {
}