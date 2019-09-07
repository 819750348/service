package com.snake.web.boot.module.rup.repository;

import com.snake.web.boot.module.rup.model.ModelInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/11/20.
 */

@Transactional
@RepositoryRestResource(path = "model")
public interface ModelInfoRepository extends JpaRepository<ModelInfo,Long> {
    @Modifying
    @Query(value = "update model_info set model_status = :model_status where id = :id",nativeQuery = true)
    Object nextModel(@Param("id") Long id, @Param("model_status") Integer model_status);


    @Query(value = "select m from ModelInfo  m where  (m.modelName  like '%:key%' or  m.modelLabel  like '%:key%' or m.modelDepartment like '%:key%' or m.developer   like '%:key%') and m.type=:type order by  m.id desc ")
    Page<ModelInfo> findAllByKeys(@Param("key") String key ,@Param("type") Integer type, Pageable pageable);
    List<ModelInfo> findByDeveloperId(Long developerId);

}
