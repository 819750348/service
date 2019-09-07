package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.rup.model.FlowStep;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface FlowStepMapper extends Mapper<FlowStep>, MySqlMapper<FlowStep> {
}