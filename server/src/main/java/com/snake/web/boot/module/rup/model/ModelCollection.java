package com.snake.web.boot.module.rup.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "model_collection")
public class ModelCollection {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模型ID
     */
    @Column(name = "model_id")
    private Long modelId;

    /**
     * 人员ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 人员名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 收藏状态[0,取消收藏;1:收藏]
     */
    @Column(name = "collect_status")
    private Boolean collectStatus;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取模型ID
     *
     * @return model_id - 模型ID
     */
    public Long getModelId() {
        return modelId;
    }

    /**
     * 设置模型ID
     *
     * @param modelId 模型ID
     */
    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    /**
     * 获取人员ID
     *
     * @return user_id - 人员ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置人员ID
     *
     * @param userId 人员ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取人员名称
     *
     * @return user_name - 人员名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置人员名称
     *
     * @param userName 人员名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取收藏状态[0,取消收藏;1:收藏]
     *
     * @return collect_status - 收藏状态[0,取消收藏;1:收藏]
     */
    public Boolean getCollectStatus() {
        return collectStatus;
    }

    /**
     * 设置收藏状态[0,取消收藏;1:收藏]
     *
     * @param collectStatus 收藏状态[0,取消收藏;1:收藏]
     */
    public void setCollectStatus(Boolean collectStatus) {
        this.collectStatus = collectStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", modelId=").append(modelId);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", createTime=").append(createTime);
        sb.append(", collectStatus=").append(collectStatus);
        sb.append("]");
        return sb.toString();
    }
}