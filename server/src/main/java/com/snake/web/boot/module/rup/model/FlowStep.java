package com.snake.web.boot.module.rup.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "flow_step")
public class FlowStep {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 流程id
     */
    @Column(name = "flow_id")
    private Long flowId;

    /**
     * 步骤名称
     */
    @Column(name = "step_name")
    private String stepName;

    /**
     * 步骤标识【通过常亮标识】
     */
    @Column(name = "step_identification")
    private Integer stepIdentification;

    /**
     * 步骤类型【0:开始节点;1:过程节点;2:结束节点】
     */
    @Column(name = "step_type")
    private Integer stepType;

    /**
     * 步骤编号
     */
    @Column(name = "step_number")
    private Integer stepNumber;

    /**
     * 审核状态【0:初始状态;1:待处理，2:审核通过;3:审核驳回】
     */
    @Column(name = "step_status")
    private Integer stepStatus;

    /**
     * 审批意见
     */
    @Column(name = "approval_opinion")
    private String approvalOpinion;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 处理人
     */
    @Column(name = "do_with_user")
    private String doWithUser;

    /**
     * 处理人ID
     */
    @Column(name = "do_with_userid")
    private Long doWithUserid;

    /**
     * 完成时间
     */
    @Column(name = "complete_time")
    private Date completeTime;

    /**
     * 待办人ID，可以多个，中间用,隔开
     */
    @Column(name = "wait_user_id")
    private String waitUserId;

    /**
     * 代办人
     */
    @Column(name = "wait_user")
    private String waitUser;

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
     * 获取流程id
     *
     * @return flow_id - 流程id
     */
    public Long getFlowId() {
        return flowId;
    }

    /**
     * 设置流程id
     *
     * @param flowId 流程id
     */
    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    /**
     * 获取步骤名称
     *
     * @return step_name - 步骤名称
     */
    public String getStepName() {
        return stepName;
    }

    /**
     * 设置步骤名称
     *
     * @param stepName 步骤名称
     */
    public void setStepName(String stepName) {
        this.stepName = stepName == null ? null : stepName.trim();
    }

    /**
     * 获取步骤标识【通过常亮标识】
     *
     * @return step_identification - 步骤标识【通过常亮标识】
     */
    public Integer getStepIdentification() {
        return stepIdentification;
    }

    /**
     * 设置步骤标识【通过常亮标识】
     *
     * @param stepIdentification 步骤标识【通过常亮标识】
     */
    public void setStepIdentification(Integer stepIdentification) {
        this.stepIdentification = stepIdentification;
    }

    /**
     * 获取步骤类型【0:开始节点;1:过程节点;2:结束节点】
     *
     * @return step_type - 步骤类型【0:开始节点;1:过程节点;2:结束节点】
     */
    public Integer getStepType() {
        return stepType;
    }

    /**
     * 设置步骤类型【0:开始节点;1:过程节点;2:结束节点】
     *
     * @param stepType 步骤类型【0:开始节点;1:过程节点;2:结束节点】
     */
    public void setStepType(Integer stepType) {
        this.stepType = stepType;
    }

    /**
     * 获取步骤编号
     *
     * @return step_number - 步骤编号
     */
    public Integer getStepNumber() {
        return stepNumber;
    }

    /**
     * 设置步骤编号
     *
     * @param stepNumber 步骤编号
     */
    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    /**
     * 获取审核状态【0:初始状态;1:审核通过;2:审核驳回】
     *
     * @return step_status - 审核状态【0:初始状态;1:审核通过;2:审核驳回】
     */
    public Integer getStepStatus() {
        return stepStatus;
    }

    /**
     * 设置审核状态【0:初始状态;1:审核通过;2:审核驳回】
     *
     * @param stepStatus 审核状态【0:初始状态;1:审核通过;2:审核驳回】
     */
    public void setStepStatus(Integer stepStatus) {
        this.stepStatus = stepStatus;
    }

    /**
     * 获取审批意见
     *
     * @return approval_opinion - 审批意见
     */
    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    /**
     * 设置审批意见
     *
     * @param approvalOpinion 审批意见
     */
    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion == null ? null : approvalOpinion.trim();
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
     * 获取处理人
     *
     * @return do_with_user - 处理人
     */
    public String getDoWithUser() {
        return doWithUser;
    }

    /**
     * 设置处理人
     *
     * @param doWithUser 处理人
     */
    public void setDoWithUser(String doWithUser) {
        this.doWithUser = doWithUser == null ? null : doWithUser.trim();
    }

    /**
     * 获取处理人ID
     *
     * @return do_with_userid - 处理人ID
     */
    public Long getDoWithUserid() {
        return doWithUserid;
    }

    /**
     * 设置处理人ID
     *
     * @param doWithUserid 处理人ID
     */
    public void setDoWithUserid(Long doWithUserid) {
        this.doWithUserid = doWithUserid;
    }

    /**
     * 获取完成时间
     *
     * @return complete_time - 完成时间
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * 设置完成时间
     *
     * @param completeTime 完成时间
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * 获取待办人ID，可以多个，中间用,隔开
     *
     * @return wait_user_id - 待办人ID，可以多个，中间用,隔开
     */
    public String getWaitUserId() {
        return waitUserId;
    }

    /**
     * 设置待办人ID，可以多个，中间用,隔开
     *
     * @param waitUserId 待办人ID，可以多个，中间用,隔开
     */
    public void setWaitUserId(String waitUserId) {
        this.waitUserId = waitUserId == null ? null : waitUserId.trim();
    }

    /**
     * 获取代办人
     *
     * @return wait_user - 代办人
     */
    public String getWaitUser() {
        return waitUser;
    }

    /**
     * 设置代办人
     *
     * @param waitUser 代办人
     */
    public void setWaitUser(String waitUser) {
        this.waitUser = waitUser == null ? null : waitUser.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", flowId=").append(flowId);
        sb.append(", stepName=").append(stepName);
        sb.append(", stepIdentification=").append(stepIdentification);
        sb.append(", stepType=").append(stepType);
        sb.append(", stepNumber=").append(stepNumber);
        sb.append(", stepStatus=").append(stepStatus);
        sb.append(", approvalOpinion=").append(approvalOpinion);
        sb.append(", createTime=").append(createTime);
        sb.append(", doWithUser=").append(doWithUser);
        sb.append(", doWithUserid=").append(doWithUserid);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", waitUserId=").append(waitUserId);
        sb.append(", waitUser=").append(waitUser);
        sb.append("]");
        return sb.toString();
    }
}