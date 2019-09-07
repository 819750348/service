package com.snake.web.boot.module.system.controller;

import com.snake.web.boot.config.ApiResultType;
import com.snake.web.boot.module.system.mapper.UserMapper;
import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.module.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HP on 2018/10/25.
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping(value = "page")
    public Object page(Integer page, Integer size, User user) {
        Pageable pageable = PageRequest.of(page != null && page >= 0 ? page : 0, size != null && size > 0 ? size : 15);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIgnorePaths("password","enabled");
        return userRepository.findAll(Example.of(user, matcher), pageable);
    }

    @ResponseBody
    @RequestMapping(value = "enable")
    public Object enable(Long id) {
        userRepository.updateEnabledById(true, id);
        return ApiResultType.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "disable")
    public Object disable(Long id) {
        userRepository.updateEnabledById(false, id);
        return ApiResultType.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "resetPassword")
    public Object resetPassword(Long id) {
        UserDetails user = userRepository.getOne(id);
        userRepository.updatePasswordById(User.password(user.getUsername()), id);
        return ApiResultType.SUCCESS;
    }

}
