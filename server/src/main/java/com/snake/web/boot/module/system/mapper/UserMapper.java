package com.snake.web.boot.module.system.mapper;

import com.snake.web.boot.module.system.model.User;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by HP on 2018/10/31.
 */

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    @Select("select * from system_user where username=#{username}")
    User findByUsername(@Param("username")String username);
}
