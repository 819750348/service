package com.snake.web.boot.module.system.service;

import com.snake.web.boot.module.resource.ResourceService;
import com.snake.web.boot.module.system.model.Role;
import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.module.system.model.UserRole;
import com.snake.web.boot.module.system.repository.UserRoleRepository;
import com.snake.web.boot.module.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by HP on 2018/10/24.
 */
@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private ResourceService resourceService;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null != user) {
            List<UserRole> userRoleList = userRoleRepository.findByUserId(user.getId());
            if (null != userRoleList && userRoleList.size() > 0) {
                Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                Set<Role> roles = new HashSet<Role>();
                ResourceService.ResourceMap<Long, Role, Role> roleResourceMap = resourceService.getResourceMap(ResourceService.KEY_ROLE_ID_VALUE_ROLE);
                for (UserRole userRole : userRoleList) {
                    Role role = roleResourceMap.get(userRole.getRoleId());
                    if (null != role) {
                        authorities.add(new SimpleGrantedAuthority(role.getCode()));
                        roles.add(role);
                    }
                }
                user.setRoles(roles);
                user.setAuthorities(authorities);
            }
            return user;
        }
        return null;
    }
}
