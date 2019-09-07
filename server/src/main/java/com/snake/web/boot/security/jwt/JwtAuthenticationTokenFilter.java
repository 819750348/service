package com.snake.web.boot.security.jwt;

import com.snake.web.boot.module.system.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by HP on 2018/10/25.
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String header_token_name = "Authorization";
    private static final String token_head = "token";

    private UserService userService;
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationTokenFilter(UserService userService, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(header_token_name);
        if (null != authHeader && authHeader.startsWith(token_head)) {
            String token = authHeader.substring(token_head.length() + authHeader.indexOf(token_head));
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (username != null) {
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    if (null != userDetails && userDetails.isEnabled()) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
//                        authenticationToken.setDetails(userDetails);
//                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    } else {
//                        throw new ServletException("user not exists ! ");
                    }
                }
//                return;
            }
        }
        filterChain.doFilter(request, response);
//        throw new ServletException("authentication token error ! ");
    }
}
