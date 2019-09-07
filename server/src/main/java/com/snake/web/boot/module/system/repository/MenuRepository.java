package com.snake.web.boot.module.system.repository;

import com.snake.web.boot.module.system.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

/**
 * Created by HP on 2018/10/26.
 */
@RepositoryRestController
@RepositoryRestResource(path = "menu")
public interface MenuRepository extends JpaRepository<Menu,Long> {
}
