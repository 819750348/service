package com.snake.web.boot.module.system.repository;

import com.snake.web.boot.module.system.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HP on 2018/11/16.
 */
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {
}
