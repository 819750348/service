package com.snake.web.boot.module.controller;

import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.module.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * Created by HP on 2018/11/7.
 */
public class BasicController {

    @Autowired
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected User loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.loadUserByUsername(authentication.getName());
    }

    protected Collection<GrantedAuthority> loginUserConfigs() {
        return loginUser().getAuthorities();
    }

    protected Long loginUserId() {
        return loginUser().getId();
    }

}
