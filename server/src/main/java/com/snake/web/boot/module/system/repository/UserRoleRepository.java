package com.snake.web.boot.module.system.repository;

import com.snake.web.boot.module.system.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by HP on 2018/10/26.
 */
@RepositoryRestResource(path = "user_role")
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    @RestResource(path = "findByUserId",rel = "findByUserId")
    List<UserRole> findByUserId(@Param("userId") Long userId);

    @Modifying
    @RestResource(path = "delete",rel = "delete")
    void delete(@RequestBody UserRole userRole);


}
