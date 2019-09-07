package com.snake.web.boot.module.system.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by HP on 2018/10/26.
 */
@Entity
@Table(name = "system_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long roleId;
    private Date createTime;

//    @ManyToOne
//    @JoinColumn(name = "userId", insertable = false, updatable = false)
//    private User user;
//    @ManyToOne
//    @JoinColumn(name = "roleId", insertable = false, updatable = false)
//    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }

//    public Role getRole() {
//        return role;
//    }

//    public void setRole(Role role) {
//        this.role = role;
//    }
}
