package com.snake.web.boot.module.system.controller;

import com.snake.web.boot.module.system.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HP on 2018/11/26.
 */
@RestController
@RequestMapping("/permission/")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionRepository getPermissionRepository() {
        return permissionRepository;
    }

    @ResponseBody
    @RequestMapping(value = "all")
    public Object all() {
        return permissionRepository.findAll();
    }
}
