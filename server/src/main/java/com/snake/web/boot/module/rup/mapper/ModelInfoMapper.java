package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.rup.model.ModelInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface ModelInfoMapper extends Mapper<ModelInfo>, MySqlMapper<ModelInfo> {
    List<ModelInfo> selectModelInfoByKeys(@Param("keys")String keys, @Param("type")Long type);

    List<ModelInfo> selectMyCollections(@Param("userid")Long userid);

    List<ModelInfo> selectMyDowns(@Param("userid")Long userid);

    List<ModelInfo> selectMyUploads(@Param("userid")Long userid);

    List<ModelInfo> selectMyWarehousing(@Param("userid")Long userid,@Param("category")Long category);

    List<ModelInfo> myCompletedWarehousing(@Param("userid")Long userid,@Param("category")Long category,@Param("keys")String keys);

    Long selectMaxId();

}