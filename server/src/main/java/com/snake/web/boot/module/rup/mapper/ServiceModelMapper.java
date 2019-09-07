package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.rup.model.ServiceInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface ServiceModelMapper extends Mapper<ServiceInfo>, MySqlMapper<ServiceInfo> {
    List<ServiceInfo> getServiceModelListAll(@Param("keys")String keys);
    List<ServiceInfo> selectLabelServiceUsers(@Param("labelid")String labelid, @Param("keys")String keys);
    List<ServiceInfo> searchServicekeys(@Param("keys")String keys);
}
