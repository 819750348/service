package com.snake.web.boot.security;

import com.snake.web.boot.Application;
import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.module.system.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by HP on 2018/10/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class BCryptPasswordEncoderTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void encodePasswordTest() {
        String password = new BCryptPasswordEncoder().encode("123456");
        System.out.println(password);
    }

    @Test
    public void updatePasswordTest() {
        User user = userRepository.findByUsername("wenxueyong");
        user.setPasswordAndEncode("123456");
//        userRepository.save(user);
    }
}
