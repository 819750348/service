package com.snake.web.boot.module.system.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by HP on 2018/7/2.
 */
@Entity
@Table(name = "system_user")
public class User implements UserDetails,Serializable {

    @Id
    @JsonAlias("userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;


    //名称
    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "contact_information")
    @Getter
    @Setter
    private String contactInformation;

    @Column(name = "department")
    @Getter
    @Setter
    private String department;

    @Column(name = "is_leader")
    @Getter
    @Setter
    private Boolean isLeader;

    @Column(name = "reason")
    @Getter
    @Setter
    private String reason;

    @Column(name = "label")
    @Getter
    @Setter
    private String label;

    @Column(name = "remark")
    @Getter
    @Setter
    private String remark;


    @Column(name = "is_admin")
    @Getter
    @Setter
    private Boolean isAdmin;

    @Column(name = "is_tester")
    @Getter
    @Setter
    private Boolean isTester;

    @Column(name = "is_evaluator")
    @Getter
    @Setter
    private Boolean isEvaluator;

    //装备、作战、环境、评估、支撑 测试类型
    @Column(name = "test_equipment")
    @Getter
    @Setter
    private Boolean testEquipment;

    //作战
    @Column(name = "test_cambot")
    @Getter
    @Setter
    private Boolean testCambot;

    //环境
    @Column(name = "test_environment")
    @Getter
    @Setter
    private Boolean testEnvironment;

    //评估
    @Column(name = "test_evaluation")
    @Getter
    @Setter
    private Boolean testEvaluation;

    //支撑
    @Column(name = "test_support")
    @Getter
    @Setter
    private Boolean testSupport;

    //装备、作战、环境、评估、支撑 评估类型
    //装备
    @Column(name = "evaluate_equipment")
    @Getter
    @Setter
    private Boolean evaluateEquipment;
    //作战
    @Column(name = "evaluate_cambot")
    @Getter
    @Setter
    private Boolean evaluateCambot;
    //环境
    @Column(name = "evaluate_environment")
    @Getter
    @Setter
    private Boolean evaluateEnvironment;

    //评估
    @Column(name = "evaluate_evaluation")
    @Getter
    @Setter
    private Boolean evaluateEvaluation;

    //支撑
    @Column(name = "evaluate_support")
    @Getter
    @Setter
    private Boolean evaluateSupport;



//    @OneToMany(targetEntity = UserRole.class, mappedBy = "user")
    @Transient
    private Set<UserRole> userRoles;
    @Transient
    private Set<Role> roles;

    @Transient
    private Collection<GrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String password(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    public void setPasswordAndEncode(String password) {
        this.password = User.password(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return null == this.enabled ? true : this.enabled;
    }



}
