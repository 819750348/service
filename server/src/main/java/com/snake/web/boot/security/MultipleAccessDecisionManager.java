package com.snake.web.boot.security;

import com.snake.web.boot.Constants;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by HP on 2018/10/25.
 */
@Component
public class MultipleAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configs) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configs || configs.size() <= 0) {
            return;
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        if (null != request) {
            String method = request.getMethod();
            Iterator<ConfigAttribute> iterator = configs.iterator();
            while (iterator.hasNext()) {
                RestSecurityConfig config = (RestSecurityConfig) iterator.next();
                String needRole = config.getAttribute();
                if (config.getMethod().toUpperCase().equals(Constants.REQUEST_METHOD_ALL) || config.getMethod().toUpperCase().equals(method.toUpperCase())) {
                    for (GrantedAuthority authority : authentication.getAuthorities()) {//authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                        if (Constants.isSuperAdmin(authority.getAuthority()) || needRole.trim().equals(authority.getAuthority())) {
                            return;
                        }
                    }
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * Created by HP on 2018/10/26.
     */
    public static class RestSecurityConfig extends SecurityConfig {
        private String method;

        public RestSecurityConfig(String config, String method) {
            super(config);
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }
}
