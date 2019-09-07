package com.snake.web.boot.module.resource;


import com.snake.web.boot.Constants;
import com.snake.web.boot.module.system.model.*;
import com.snake.web.boot.module.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by HP on 2018/11/6.
 */
@Service
public class ResourceService {

    public abstract class ResourceMap<K, V, M> extends HashMap<K, V> {

        public void build() {
            List<M> list = getRepository().findAll();
            for (M each : list) {
                put(each);
            }
        }

        abstract JpaRepository<M, Long> getRepository();

        public abstract void put(M object);

    }

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private MenuRoleRepository menuRoleRepository;
    @Autowired
    private PermissionRoleRepository permissionRoleRepository;

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void setPermissionRepository(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public void setMenuRoleRepository(MenuRoleRepository menuRoleRepository) {
        this.menuRoleRepository = menuRoleRepository;
    }

    public void setPermissionRoleRepository(PermissionRoleRepository permissionRoleRepository) {
        this.permissionRoleRepository = permissionRoleRepository;
    }

    class KeyRoleResourceMap extends ResourceMap<Long, Role, Role> {

        @Override
        public JpaRepository<Role, Long> getRepository() {
            return roleRepository;
        }

        @Override
        public void build() {
            super.build();
            this.put(Constants.ROLE_SUPER_ADMIN);
        }

        @Override
        public void put(Role object) {
            super.put(object.getId(), object);
        }
    }

    class CodeRoleResourceMap extends ResourceMap<String, Role, Role> {

        @Override
        public JpaRepository<Role, Long> getRepository() {
            return roleRepository;
        }

        @Override
        public void put(Role object) {
            super.put(object.getCode(), object);
        }
    }

    class KeyMenuResourceMap extends ResourceMap<Long, Menu, Menu> {

        @Override
        public JpaRepository<Menu, Long> getRepository() {
            return menuRepository;
        }

        @Override
        public void put(Menu object) {
            super.put(object.getId(), object);
        }
    }

    class KeyPermissionResourceMap extends ResourceMap<Long, Permission, Permission> {

        @Override
        public JpaRepository<Permission, Long> getRepository() {
            return permissionRepository;
        }

        @Override
        public void put(Permission object) {
            super.put(object.getId(), object);
        }
    }

    class RolePermissionRoleResourceMap extends ResourceMap<Long, Collection<PermissionRole>, PermissionRole> {

        @Override
        public JpaRepository<PermissionRole, Long> getRepository() {
            return permissionRoleRepository;
        }

        @Override
        public void put(PermissionRole object) {
            Long key = object.getRoleId();
            if (get(key) == null) {
                super.put(key, new HashSet<PermissionRole>());
            }
            Collection<PermissionRole> collection = (Collection<PermissionRole>) get(key);
            collection.add(object);
        }
    }

    class PermissionPermissionRoleResourceMap extends ResourceMap<Long, Collection<PermissionRole>, PermissionRole> {

        @Override
        public JpaRepository<PermissionRole, Long> getRepository() {
            return permissionRoleRepository;
        }

        @Override
        public void put(PermissionRole object) {
            Long key = object.getPermissionId();
            if (get(key) == null) {
                super.put(key, new HashSet<PermissionRole>());
            }
            Collection<PermissionRole> collection = (Collection<PermissionRole>) get(key);
            collection.add(object);
        }
    }

    class RoleMenuRoleResourceMap extends ResourceMap<Long, Collection<MenuRole>, MenuRole> {

        @Override
        public JpaRepository<MenuRole, Long> getRepository() {
            return menuRoleRepository;
        }

        @Override
        public void put(MenuRole object) {
            Long key = object.getRoleId();
            if (get(key) == null) {
                super.put(key, new HashSet<MenuRole>());
            }
            Collection<MenuRole> collection = (Collection<MenuRole>) get(key);
            collection.add(object);
        }
    }

    class MenuMenuRoleResourceMap extends ResourceMap<Long, Collection<MenuRole>, MenuRole> {

        @Override
        public JpaRepository<MenuRole, Long> getRepository() {
            return menuRoleRepository;
        }

        @Override
        public void put(MenuRole object) {
            Long key = object.getMenuId();
            if (get(key) == null) {
                super.put(key, new HashSet<MenuRole>());
            }
            Collection<MenuRole> collection = (Collection<MenuRole>) get(key);
            collection.add(object);
        }
    }

    public static final String KEY_ROLE_CODE_VALUE_ROLE = "{key:role_code,value:role}-resource";
    public static final String KEY_ROLE_ID_VALUE_ROLE = "{key:role_id,value:role}-resource";
    public static final String KEY_MENU_ID_VALUE_MENU = "{key:menu_id,value:menu}-resource";
    public static final String KEY_PERMISSION_ID_VALUE_PERMISSION = "{key:permission_id,value:permission}-resource";
    public static final String KEY_ROLE_ID_VALUE_PERMISSION_ROLE = "{key:role_id,value:permission_role}-resource";
    public static final String KEY_PERMISSION_ID_VALUE_PERMISSION_ROLE = "{key:permission_id,value:permission_role}-resource";
    public static final String KEY_ROLE_ID_VALUE_MENU_ROLE = "{key:role_id,value:menu_role}-resource";
    public static final String KEY_MENU_ID_VALUE_MENU_ROLE = "{key:menu_id,value:menu_role}-resource";

    private static boolean build = false;

    private static Map<String, ResourceMap> resourceMap = new HashMap<String, ResourceMap>();

    public void build() {
        resourceMap.put(KEY_ROLE_CODE_VALUE_ROLE, new CodeRoleResourceMap());
        resourceMap.put(KEY_ROLE_ID_VALUE_ROLE, new KeyRoleResourceMap());
        resourceMap.put(KEY_MENU_ID_VALUE_MENU, new KeyMenuResourceMap());
        resourceMap.put(KEY_PERMISSION_ID_VALUE_PERMISSION, new KeyPermissionResourceMap());
        resourceMap.put(KEY_ROLE_ID_VALUE_PERMISSION_ROLE, new RolePermissionRoleResourceMap());
        resourceMap.put(KEY_PERMISSION_ID_VALUE_PERMISSION_ROLE, new PermissionPermissionRoleResourceMap());
        resourceMap.put(KEY_ROLE_ID_VALUE_MENU_ROLE, new RoleMenuRoleResourceMap());
        resourceMap.put(KEY_MENU_ID_VALUE_MENU_ROLE, new MenuMenuRoleResourceMap());
        for (ResourceMap map : resourceMap.values()) {
            map.build();
        }
        build = true;
    }

    public void rebuild() {
        resourceMap.clear();
        build = false;
        build();
    }

    public boolean isBuild() {
        return build;
    }

    public <K, V, M> ResourceMap<K, V, M> getResourceMap(String key) {
        if(!build){
            build();
        }
        return resourceMap.get(key);
    }

}
