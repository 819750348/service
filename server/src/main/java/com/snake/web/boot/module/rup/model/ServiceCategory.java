package com.snake.web.boot.module.rup.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ServiceCategory {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    *分类名称
    * */
    @Column(name = "name")
    private String name;

    /*
    * 父级id
    * */
    @Column(name = "parent_id")
    private Long parentId;


    /*
    * 创建时间
    * */
    @Column(name = "create_time")
    private String createTime;

    /*
    *创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /*
     *创建人id
     */
    @Column(name = "create_userid")
    private Long createUserid;

    /*
    * 更新时间
    * */
    @Column(name = "update_time")
    private String updateTime;

    /*
    *更新人
    * */
    @Column(name = "update_user")
    private String updateUser;

    /*
     *更新人id
     * */
    @Column(name = "update_userid")
    private String updateUserid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Long createUserid) {
        this.createUserid = createUserid;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

}
