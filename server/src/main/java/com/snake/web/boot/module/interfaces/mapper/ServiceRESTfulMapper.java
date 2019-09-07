package com.snake.web.boot.module.interfaces.mapper;

import com.snake.web.boot.module.interfaces.model.RestfulBean;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface ServiceRESTfulMapper extends Mapper<RestfulBean>, MySqlMapper<RestfulBean> {
}
