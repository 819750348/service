package com.snake.web.boot.module.rup.repository;


import com.snake.web.boot.module.rup.model.BasicParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicParamRepository  extends JpaRepository<BasicParam, Long> {
}
