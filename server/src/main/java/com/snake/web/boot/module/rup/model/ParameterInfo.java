package com.snake.web.boot.module.rup.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "parameter_info")
public class ParameterInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 参数编号
     */
    @Column(name = "parameter_code")
    private String parameterCode;

    /**
     * 模型编号
     */
    @Column(name = "model_code")
    private String modelCode;

    /**
     * 模型ID
     */
    @Column(name = "model_id")
    private Long modelId;

    /**
     * 参数名称
     */
    private String name;

    /**
     * 参数编写人ID
     */
    @Column(name = "developer_id")
    private Long developerId;

    /**
     * 联系方式
     */
    @Column(name = "contact_information")
    private String contactInformation;

    /**
     * 参数上传者
     */
    private Long developer;

    /**
     * 参数编写时间
     */
    @Column(name = "write_time")
    private Date writeTime;

    /**
     * 参数上传单位
     */
    private String department;

    /**
     * 联系方式下载者可见【0:不可见;1:可见】
     */
    @Column(name = "contact_information_isvisible")
    private Boolean contactInformationIsvisible;

    /**
     * 参数开放权限[0:A级（所有用户都可见）;1:B级（注册用户均可下载）;2:C级（注册用户可预览，需要申请下载权限）;3:D级（仅管理员可以下载）]
     */
    @Column(name = "parameter_permissions")
    private Boolean parameterPermissions;

    /**
     * 标签
     */
    @Column(name = "parameter_label")
    private String parameterLabel;

    /**
     * 阅读量
     */
    private Integer pv;

    /**
     * 下载量
     */
    @Column(name = "download_count")
    private Integer downloadCount;

    /**
     * 参数描述文件
     */
    @Column(name = "description_file_id")
    private Long descriptionFileId;

    /**
     * 参数状态【0：草稿;1:待审核;2:通过;3:驳回】
     */
    @Column(name = "parameter_status")
    private String parameterStatus;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
    @Column(name = "update_user_id")
    private String updateUserId;

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
     * 获取参数编号
     *
     * @return parameter_code - 参数编号
     */
    public String getParameterCode() {
        return parameterCode;
    }

    /**
     * 设置参数编号
     *
     * @param parameterCode 参数编号
     */
    public void setParameterCode(String parameterCode) {
        this.parameterCode = parameterCode == null ? null : parameterCode.trim();
    }

    /**
     * 获取模型编号
     *
     * @return model_code - 模型编号
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * 设置模型编号
     *
     * @param modelCode 模型编号
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode == null ? null : modelCode.trim();
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
     * 获取参数名称
     *
     * @return name - 参数名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置参数名称
     *
     * @param name 参数名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取参数编写人ID
     *
     * @return developer_id - 参数编写人ID
     */
    public Long getDeveloperId() {
        return developerId;
    }

    /**
     * 设置参数编写人ID
     *
     * @param developerId 参数编写人ID
     */
    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    /**
     * 获取联系方式
     *
     * @return contact_information - 联系方式
     */
    public String getContactInformation() {
        return contactInformation;
    }

    /**
     * 设置联系方式
     *
     * @param contactInformation 联系方式
     */
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation == null ? null : contactInformation.trim();
    }

    /**
     * 获取参数上传者
     *
     * @return developer - 参数上传者
     */
    public Long getDeveloper() {
        return developer;
    }

    /**
     * 设置参数上传者
     *
     * @param developer 参数上传者
     */
    public void setDeveloper(Long developer) {
        this.developer = developer;
    }

    /**
     * 获取参数编写时间
     *
     * @return write_time - 参数编写时间
     */
    public Date getWriteTime() {
        return writeTime;
    }

    /**
     * 设置参数编写时间
     *
     * @param writeTime 参数编写时间
     */
    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    /**
     * 获取参数上传单位
     *
     * @return department - 参数上传单位
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 设置参数上传单位
     *
     * @param department 参数上传单位
     */
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    /**
     * 获取联系方式下载者可见【0:不可见;1:可见】
     *
     * @return contact_information_isvisible - 联系方式下载者可见【0:不可见;1:可见】
     */
    public Boolean getContactInformationIsvisible() {
        return contactInformationIsvisible;
    }

    /**
     * 设置联系方式下载者可见【0:不可见;1:可见】
     *
     * @param contactInformationIsvisible 联系方式下载者可见【0:不可见;1:可见】
     */
    public void setContactInformationIsvisible(Boolean contactInformationIsvisible) {
        this.contactInformationIsvisible = contactInformationIsvisible;
    }

    /**
     * 获取参数开放权限[0:A级（所有用户都可见）;1:B级（注册用户均可下载）;2:C级（注册用户可预览，需要申请下载权限）;3:D级（仅管理员可以下载）]
     *
     * @return parameter_permissions - 参数开放权限[0:A级（所有用户都可见）;1:B级（注册用户均可下载）;2:C级（注册用户可预览，需要申请下载权限）;3:D级（仅管理员可以下载）]
     */
    public Boolean getParameterPermissions() {
        return parameterPermissions;
    }

    /**
     * 设置参数开放权限[0:A级（所有用户都可见）;1:B级（注册用户均可下载）;2:C级（注册用户可预览，需要申请下载权限）;3:D级（仅管理员可以下载）]
     *
     * @param parameterPermissions 参数开放权限[0:A级（所有用户都可见）;1:B级（注册用户均可下载）;2:C级（注册用户可预览，需要申请下载权限）;3:D级（仅管理员可以下载）]
     */
    public void setParameterPermissions(Boolean parameterPermissions) {
        this.parameterPermissions = parameterPermissions;
    }

    /**
     * 获取标签
     *
     * @return parameter_label - 标签
     */
    public String getParameterLabel() {
        return parameterLabel;
    }

    /**
     * 设置标签
     *
     * @param parameterLabel 标签
     */
    public void setParameterLabel(String parameterLabel) {
        this.parameterLabel = parameterLabel == null ? null : parameterLabel.trim();
    }

    /**
     * 获取阅读量
     *
     * @return pv - 阅读量
     */
    public Integer getPv() {
        return pv;
    }

    /**
     * 设置阅读量
     *
     * @param pv 阅读量
     */
    public void setPv(Integer pv) {
        this.pv = pv;
    }

    /**
     * 获取下载量
     *
     * @return download_count - 下载量
     */
    public Integer getDownloadCount() {
        return downloadCount;
    }

    /**
     * 设置下载量
     *
     * @param downloadCount 下载量
     */
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * 获取参数描述文件
     *
     * @return description_file_id - 参数描述文件
     */
    public Long getDescriptionFileId() {
        return descriptionFileId;
    }

    /**
     * 设置参数描述文件
     *
     * @param descriptionFileId 参数描述文件
     */
    public void setDescriptionFileId(Long descriptionFileId) {
        this.descriptionFileId = descriptionFileId;
    }

    /**
     * 获取参数状态【0：草稿;1:待审核;2:通过;3:驳回】
     *
     * @return parameter_status - 参数状态【0：草稿;1:待审核;2:通过;3:驳回】
     */
    public String getParameterStatus() {
        return parameterStatus;
    }

    /**
     * 设置参数状态【0：草稿;1:待审核;2:通过;3:驳回】
     *
     * @param parameterStatus 参数状态【0：草稿;1:待审核;2:通过;3:驳回】
     */
    public void setParameterStatus(String parameterStatus) {
        this.parameterStatus = parameterStatus == null ? null : parameterStatus.trim();
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
     * @return update_user_id - 更新人ID
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置更新人ID
     *
     * @param updateUserId 更新人ID
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parameterCode=").append(parameterCode);
        sb.append(", modelCode=").append(modelCode);
        sb.append(", modelId=").append(modelId);
        sb.append(", name=").append(name);
        sb.append(", developerId=").append(developerId);
        sb.append(", contactInformation=").append(contactInformation);
        sb.append(", developer=").append(developer);
        sb.append(", writeTime=").append(writeTime);
        sb.append(", department=").append(department);
        sb.append(", contactInformationIsvisible=").append(contactInformationIsvisible);
        sb.append(", parameterPermissions=").append(parameterPermissions);
        sb.append(", parameterLabel=").append(parameterLabel);
        sb.append(", pv=").append(pv);
        sb.append(", downloadCount=").append(downloadCount);
        sb.append(", descriptionFileId=").append(descriptionFileId);
        sb.append(", parameterStatus=").append(parameterStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append("]");
        return sb.toString();
    }
}