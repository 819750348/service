package com.snake.web.boot.module.rup.repository;


import com.snake.web.boot.module.rup.model.DataPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface DataPermissionRepository  extends JpaRepository<DataPermission, Long> {
    DataPermission findByPermissionType(String permissionType);
}
