package com.snake.web.boot.module.system.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by HP on 2018/10/26.
 */
@Entity
@Table(name = "system_permission_role")
public class PermissionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @ManyToOne(targetEntity = Url.class,cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    private Long permissionId;
    //    @ManyToOne(targetEntity = Role.class,cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    private Long roleId;
    private Date createTime;

//    @ManyToOne
//    @JoinColumn(name = "permissionId", insertable = false, updatable = false)
//    private Permission permission;
//    @ManyToOne
//    @JoinColumn(name = "roleId", insertable = false, updatable = false)
//    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    public Permission getPermission() {
//        return permission;
//    }

//    public void setPermission(Permission permission) {
//        this.permission = permission;
//    }

//    public Role getRole() {
//        return role;
//    }

//    public void setRole(Role role) {
//        this.role = role;
//    }
}
