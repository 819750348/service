package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.rup.model.BaseLabel;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface BaseLabelMapper extends Mapper<BaseLabel>, MySqlMapper<BaseLabel> {

    List<BaseLabel> selectLabels() ;
}