package com.snake.web.boot.module.system.controller;

import com.snake.web.boot.module.system.repository.MenuRepository;
import com.snake.web.boot.module.system.repository.MenuRoleRepository;
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
 * Created by HP on 2018/11/6.
 */
@RestController
@RequestMapping("/menu/")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @ResponseBody
    @RequestMapping(value = "all")
    public Object all() {
        return menuRepository.findAll();
    }
}
