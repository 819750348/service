package com.snake.web.boot.module.rup.repository;

import com.snake.web.boot.module.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HP on 2018/7/2.
 */
@Repository()
public interface BusinessUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
