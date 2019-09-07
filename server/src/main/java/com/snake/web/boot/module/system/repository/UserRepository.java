package com.snake.web.boot.module.system.repository;

import com.snake.web.boot.module.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by HP on 2018/7/2.
 */
@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findByDepartmentAndIsLeader(String department, Boolean isLeader);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.enabled=?1 where u.id=?2")
    void updateEnabledById(Boolean enabled, Long id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.password=?1 where u.id=?2")
    void updatePasswordById(String password, Long id);
}
