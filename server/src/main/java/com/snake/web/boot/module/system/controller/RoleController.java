package com.snake.web.boot.module.system.controller;

import com.snake.web.boot.module.system.model.Role;
import com.snake.web.boot.module.system.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HP on 2018/11/19.
 */
@RestController
@RequestMapping("/role/")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @ResponseBody
    @RequestMapping(value = "page")
    public Object page(Integer page, Integer size, Role role) {
        Pageable pageable = PageRequest.of(page != null && page >= 0 ? page : 0, size != null && size > 0 ? size : 15);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith());
        return roleRepository.findAll(Example.of(role, matcher), pageable);
    }
}
