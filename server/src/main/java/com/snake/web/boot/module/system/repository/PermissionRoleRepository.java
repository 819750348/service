package com.snake.web.boot.module.system.repository;

import com.snake.web.boot.module.system.model.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by HP on 2018/10/26.
 */
@RepositoryRestResource(path = "permission_role")
public interface PermissionRoleRepository extends JpaRepository<PermissionRole,Long> {
    @RestResource(path = "findByRoleId",rel = "findByRoleId")
    List<PermissionRole> findByRoleId(@Param("roleId") Long roleId);
}
