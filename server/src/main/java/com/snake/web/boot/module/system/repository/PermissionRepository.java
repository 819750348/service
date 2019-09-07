package com.snake.web.boot.module.system.repository;

import com.snake.web.boot.module.system.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by HP on 2018/10/26.
 */
@RepositoryRestResource(path = "permission")
public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
