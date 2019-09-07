package com.snake.web.boot.module.rup.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Table(name = "flow_inst")
public class FlowInst {


    @Setter
    @Getter
    @Transient
    private  String modelName ;

    @Setter
    @Getter
    @Transient
    private  String modelCode ;

    @Setter
    @Getter
    @Transient
    private  Long modelId ;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 业务ID
     */
    @Column(name = "business_id")
    private Long businessId;

    /**
     * 流程类型
     */
    @Column(name = "flow_type")
    private Integer flowType;
    /**
     * 流程实例名称
     */
    @Column(name = "flow_name")
    private String flowName;

    /**
     * 流程状态[0:草稿;1:待审核;2审核通过;驳回]
     */
    @Column(name = "flow_status")
    private Integer flowStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 创建人ID
     */
    @Column(name = "create_userid")
    private Long createUserid;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新人ID
     */
    @Column(name = "update_userid")
    private Long updateUserid;

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
     * 获取业务ID
     *
     * @return business_id - 业务ID
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * 设置业务ID
     *
     * @param businessId 业务ID
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * 获取流程实例名称
     *
     * @return flow_name - 流程实例名称
     */
    public String getFlowName() {
        return flowName;
    }

    /**
     * 设置流程实例名称
     *
     * @param flowName 流程实例名称
     */
    public void setFlowName(String flowName) {
        this.flowName = flowName == null ? null : flowName.trim();
    }

    /**
     * 获取流程状态[0:草稿;1:待审核;2审核通过;驳回]
     *
     * @return flow_status - 流程状态[0:草稿;1:待审核;2审核通过;驳回]
     */
    public Integer getFlowStatus() {
        return flowStatus;
    }

    /**
     * 设置流程状态[0:草稿;1:待审核;2审核通过;驳回]
     *
     * @param flowStatus 流程状态[0:草稿;1:待审核;2审核通过;驳回]
     */
    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
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
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * 获取创建人ID
     *
     * @return create_userid - 创建人ID
     */
    public Long getCreateUserid() {
        return createUserid;
    }

    /**
     * 设置创建人ID
     *
     * @param createUserid 创建人ID
     */
    public void setCreateUserid(Long createUserid) {
        this.createUserid = createUserid;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新人
     *
     * @return update_user - 更新人
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人
     *
     * @param updateUser 更新人
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * 获取更新人ID
     *
     * @return update_userid - 更新人ID
     */
    public Long getUpdateUserid() {
        return updateUserid;
    }

    /**
     * 设置更新人ID
     *
     * @param updateUserid 更新人ID
     */
    public void setUpdateUserid(Long updateUserid) {
        this.updateUserid = updateUserid;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessId=").append(businessId);
        sb.append(", flowName=").append(flowName);
        sb.append(", flowStatus=").append(flowStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", createUserid=").append(createUserid);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateUserid=").append(updateUserid);
        sb.append("]");
        return sb.toString();
    }
}