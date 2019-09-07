package com.snake.web.boot.security;

import com.snake.web.boot.module.system.model.Permission;
import com.snake.web.boot.module.system.model.PermissionRole;
import com.snake.web.boot.module.system.model.Role;
import com.snake.web.boot.module.resource.ResourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by HP on 2018/10/25.
 */
@Component
public class APISecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Map<String, Collection<ConfigAttribute>> map = null;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    private void loadResourceDefine() {
        map = new HashMap<String, Collection<ConfigAttribute>>();
//        this.resourceService.rebuild();
        ResourceService.ResourceMap<Long, Role, Role> roleIdRoleMap = resourceService.getResourceMap(ResourceService.KEY_ROLE_ID_VALUE_ROLE);
        ResourceService.ResourceMap<Long, Permission, Permission> permissionIdPermissionMap = resourceService.getResourceMap(ResourceService.KEY_PERMISSION_ID_VALUE_PERMISSION);
        ResourceService.ResourceMap<Long, Collection<PermissionRole>, PermissionRole> permissionIdPermissionRoleMap = resourceService.getResourceMap(ResourceService.KEY_PERMISSION_ID_VALUE_PERMISSION_ROLE);
        if (null != permissionIdPermissionRoleMap && permissionIdPermissionRoleMap.size() > 0) {
            for (Long permissionId : permissionIdPermissionRoleMap.keySet()) {
                Permission permission = (Permission) permissionIdPermissionMap.get(permissionId);
                if (null != permission) {
                    String pattern = permission.getPattern();
                    String method = permission.getMethod();
                    Collection<PermissionRole> permissionRoles = (Collection<PermissionRole>) permissionIdPermissionRoleMap.get(permissionId);
                    if (null != permissionRoles && permissionRoles.size() > 0) {
                        Collection<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
                        for (PermissionRole permissionRole : permissionRoles) {
                            Role role = (Role) roleIdRoleMap.get(permissionRole.getRoleId());
                            attributes.add(new MultipleAccessDecisionManager.RestSecurityConfig(role.getCode(), method));
                        }
                        map.put(pattern, attributes);
                    }
                }
            }
        }
    }

    private String getRequestPath(HttpServletRequest request) {
        String url = request.getServletPath();
        if (request.getPathInfo() != null) {
            url = url + request.getPathInfo();
        }

        return url;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) loadResourceDefine();
        if (null != object) {
            //object 中包含用户请求的request 信息
            HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
            if (null != request) {
                String requestPath = getRequestPath(request);
                if (map.containsKey(requestPath)) {
                    return map.get(requestPath);
                }
                AntPathRequestMatcher matcher;
                String resUrl;
                for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); ) {
                    resUrl = iterator.next();
                    if (StringUtils.isNotBlank(resUrl)) {
                        matcher = new AntPathRequestMatcher(resUrl);
                        if (matcher.matches(request)) {
                            return map.get(resUrl);
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
