package com.snake.web.boot.module.rup.mapper;

import com.snake.web.boot.module.system.model.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import java.util.List;

public interface BusinessUserMapper extends Mapper<User>, MySqlMapper<User> {

    List<User> selectLabelUsers(@Param("label")String label,@Param("keys")String keys);

    List<User> selectKeysUsers(@Param("keys")String keys);

}