package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.rup.model.FlowInst;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface FlowInstMapper extends Mapper<FlowInst>, MySqlMapper<FlowInst> {
    List<FlowInst> selectFlowsWait(@Param("userid")Long userid);

    List<FlowInst> selectFlowsComplete(@Param("userid")Long userid);
}