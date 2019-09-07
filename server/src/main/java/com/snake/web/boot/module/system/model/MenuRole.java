package com.snake.web.boot.module.system.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by HP on 2018/10/26.
 */
@Entity
@Table(name = "system_menu_role")
public class MenuRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long menuId;
    private Long roleId;
    private Date createTime;

//    @ManyToOne
//    @JoinColumn(name = "menuId", insertable = false, updatable = false)
//    private Menu menu;
//    @ManyToOne
//    @JoinColumn(name = "roleId", insertable = false, updatable = false)
//    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /*public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }*/
}
