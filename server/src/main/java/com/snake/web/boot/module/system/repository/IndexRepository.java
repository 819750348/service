package com.snake.web.boot.module.system.repository;

import com.snake.web.boot.module.system.model.Index;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by HP on 2018/11/13.
 */
@RepositoryRestResource(path = "index")
public interface IndexRepository extends JpaRepository<Index, Long> {

    @RestResource(path = "findByRoleId", rel = "findByRoleId")
    List<Index> findByRoleId(@Param("roleId") Long roleId);

}
