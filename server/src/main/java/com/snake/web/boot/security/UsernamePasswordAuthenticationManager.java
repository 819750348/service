package com.snake.web.boot.security;

import com.snake.web.boot.security.exception.DefectiveAuthenticationException;
import com.snake.web.boot.security.exception.PasswordErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * Created by HP on 2018/10/25.
 */
@Component
public class UsernamePasswordAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (null != authentication && null != authentication.getPrincipal()) {
            Object principal = authentication.getPrincipal();
            Object credentials = authentication.getCredentials();
            String username = null;
            String password = null;
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
                password = ((UserDetails) principal).getPassword();
            } else if (principal instanceof SecurityProperties.User) {
                username = ((SecurityProperties.User) principal).getName();
                password = ((SecurityProperties.User) principal).getPassword();
            } else {
                if (principal instanceof String) {
                    username = (String) principal;
                }
                if (credentials instanceof String) {
                    password = (String) credentials;
                }
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (null != userDetails) {
                if (new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
                    if (userDetails.isEnabled()) {
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    auth.setDetails(userDetails);
                        return auth;
                    }
                    throw new DefectiveAuthenticationException("user disable error");
                }
                throw new PasswordErrorException("password error");
            }
            throw new DefectiveAuthenticationException("user not exists");
        }
        throw new DefectiveAuthenticationException("authentication defective");
    }
}
