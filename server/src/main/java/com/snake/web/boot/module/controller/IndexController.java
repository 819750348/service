package com.snake.web.boot.module.controller;

import com.snake.web.boot.config.ApiResult;
import com.snake.web.boot.config.ApiResultType;
import com.snake.web.boot.module.system.model.Index;
import com.snake.web.boot.module.system.model.Role;
import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.module.system.repository.IndexRepository;
import com.snake.web.boot.module.system.repository.UserRepository;
import com.snake.web.boot.module.system.mapper.UserMapper;
import com.snake.web.boot.security.UsernamePasswordAuthenticationManager;
import com.snake.web.boot.security.jwt.JwtTokenUtil;
import com.snake.web.boot.utils.HashMaps;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by HP on 2018/10/22.
 */
@RestController
@RequestMapping("/all/")
public class IndexController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IndexRepository indexRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UsernamePasswordAuthenticationManager usernamePasswordAuthenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void setIndexRepository(IndexRepository indexRepository) {
        this.indexRepository = indexRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "用户登录", notes = "根据用户名和密码验证用户信息，成功返回token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = "login")
    public Object login(@RequestBody SecurityProperties.User user) {
        if (null != user) {
            try {
                Authentication authentication = usernamePasswordAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                User userDetails = (User) authentication.getPrincipal();
                String token = jwtTokenUtil.generateToken(userDetails.getUsername());
                String layout = "custom", path = "";
                Collection<Role> authorities = userDetails.getRoles();
                for (Role role : authorities) {
                    if (StringUtils.isNotBlank(role.getLayout())) {
                        layout = role.getLayout();
                    }
                    List<Index> indexs = indexRepository.findByRoleId(role.getId());
                    if (null != indexs && indexs.size() > 0 && StringUtils.isNotBlank(indexs.get(0).getPath())) {
                        path = indexs.get(0).getPath();
                    }
                    if ("manage".equals(layout)) {
                        break;
                    }
                }
                return HashMaps.asHashMap(new String[]{"userId","layout", "token", "index"}, new String[]{userDetails.getId()+"",layout, token, path});
            } catch (AuthenticationException e) {
//                e.printStackTrace();
                return ApiResult.error(ApiResultType.AUTH_ERROR,e.getMessage());
            }
        }
        return ApiResultType.ILLEGAL_PARAMETER;
    }

    @ApiOperation(value = "用户登出", notes = "退出登录")
    @ResponseBody
    @RequestMapping(value = "logout")
    public Object logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return ApiResultType.SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "refreshToken")
    public String refreshToken(String oldToken) {
        if (!jwtTokenUtil.isTokenExpired(oldToken)) {
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return "0";
    }

    @ResponseBody
    @RequestMapping(value = "getUserById")
    public Object getUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @ResponseBody
    @GetMapping(path = "/error")
    public String error() {
        return "{\"message\":\"error happen !!! can not found anything by url\"}";
    }

}
