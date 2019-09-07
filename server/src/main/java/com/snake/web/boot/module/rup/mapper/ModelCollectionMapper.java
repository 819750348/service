package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.rup.model.ModelCollection;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface ModelCollectionMapper extends Mapper<ModelCollection>, MySqlMapper<ModelCollection> {
}