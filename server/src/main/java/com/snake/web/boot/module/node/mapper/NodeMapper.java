package com.snake.web.boot.module.node.mapper;

import com.snake.web.boot.module.node.model.NodeInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface NodeMapper extends Mapper<NodeInfo>, MySqlMapper<NodeInfo> {
       List<NodeInfo> selectKeyword1(@Param(value="keys")String keys);
       List<NodeInfo> changePage1(@Param(value="start")String start,@Param(value="pageSize")String pageSize,@Param(value="keys")String keys);

}
