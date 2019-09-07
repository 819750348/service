package com.snake.web.boot.module.resource;

import com.snake.web.boot.Constants;
import com.snake.web.boot.module.controller.BasicController;
import com.snake.web.boot.module.system.model.Menu;
import com.snake.web.boot.module.system.model.MenuRole;
import com.snake.web.boot.module.system.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by HP on 2018/11/7.
 */
@RestController
@RequestMapping("/resource/")
public class ResourceController extends BasicController {

    @Autowired
    private ResourceService resourceService;


    @RequestMapping("refresh")
    public void refresh() {
        resourceService.rebuild();
    }

    public boolean contains(Collection<MenuRole> source, Long roleId) {
        if (null != source && source.size() > 0 && roleId > 0) {
            for (MenuRole menuRole : source) {
                if (roleId == menuRole.getRoleId()) {
                    return true;
                }
            }
        }
        return false;
    }

    @GetMapping("myMenu")
    public Object myMenu() {
        Collection<GrantedAuthority> authorities = loginUserConfigs();
        List<Menu> menuList = new ArrayList<Menu>();
        if (null != authorities && authorities.size() > 0) {
            ResourceService.ResourceMap<Long, Menu, Menu> menuResourceMap = resourceService.getResourceMap(ResourceService.KEY_MENU_ID_VALUE_MENU);
            ResourceService.ResourceMap<String, Role, Role> codeRoleResourceMap = resourceService.getResourceMap(ResourceService.KEY_ROLE_CODE_VALUE_ROLE);
            ResourceService.ResourceMap<Long, Collection<MenuRole>, Menu> menuMenuRoleResourceMap = resourceService.getResourceMap(ResourceService.KEY_MENU_ID_VALUE_MENU_ROLE);
            for (Menu menu : menuResourceMap.values()) {
                if (null != menu) {
                    Collection<MenuRole> menuRoles = menuMenuRoleResourceMap.get(menu.getId());
                    for (GrantedAuthority authority : authorities) {
                        Role role = codeRoleResourceMap.get(authority.getAuthority());
                        if (Constants.isSuperAdmin(authority.getAuthority()) || null != role && contains(menuRoles, role.getId())) {
                            menuList.add(menu);
                            break;
                        }
                    }
                }
            }
        }
        return menuList;
    }

}
