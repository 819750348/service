package com.snake.web.boot.module.rup.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "model_info", schema = "snake_db", catalog = "")
public class ModelInfo {
    private long id;
    private String modelName;
    private Long developerId;
    private String contactInformation;
    private Long category;
    private Long type;
    private String developer;
    private String writeTime;
    private String modelDepartment;
    private Boolean contactInformationIsvisible;
    private Integer modelPermissions;
    private String modelLabel;
    private Integer pv;
    private Integer downloadCount;
    private Long modelFileId;
    private String modelExecuteType;
    private String modelDeveloperPlatform;
    private String modelRuntimePlatform;
    private Long modelManualId;
    private Long modelTestReportId;
    private Long modelAssessmentReportId;
    private Integer modelStatus;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String updateUser;
    private Long updateUserId;
    private String description;
    //模型编码
    @Getter
    @Setter
    @Column(name = "model_code", nullable = true, length = 50)
    private String modelCode;

    @Getter
    @Setter
    @Column(name = "other_remark", nullable = true, length = 100)
    private String otherRemark;

    //垂直分类，1：完整模型 2：模型组件 3:模型数据
    @Getter
    @Setter
    @Column(name = "classification")
    private  Integer classification;


    @Transient
    private List<ModelUser> testers;


    @Transient
    private List<ModelUser> evaluators;

    @Transient
    private  String modelFileName;
    @Transient
    private String  modelManualName;
    @Transient
    private String  modelTestReportName;
    @Transient
    private String  modelAssessmentReportName;
    @Transient
    public String getModelFileName() {
        return modelFileName;
    }
    @Transient
    public void setModelFileName(String modelFileName) {
        this.modelFileName = modelFileName;
    }
    @Transient
    public String getModelManualName() {
        return modelManualName;
    }
    @Transient
    public void setModelManualName(String modelManualName) {
        this.modelManualName = modelManualName;
    }
    @Transient
    public String getModelTestReportName() {
        return modelTestReportName;
    }
    @Transient
    public void setModelTestReportName(String modelTestReportName) {
        this.modelTestReportName = modelTestReportName;
    }
    @Transient
    public String getModelAssessmentReportName() {
        return modelAssessmentReportName;
    }
    @Transient
    public void setModelAssessmentReportName(String modelAssessmentReportName) {
        this.modelAssessmentReportName = modelAssessmentReportName;
    }


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "model_name", nullable = true, length = 50)
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Basic
    @Column(name = "developer_id", nullable = true)
    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    @Basic
    @Column(name = "contact_information", nullable = true, length = 40)
    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Basic
    @Column(name = "category", nullable = true)
    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Basic
    @Column(name = "developer", nullable = true, length = 20)
    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Basic
    @Column(name = "write_time", nullable = true, length = 30)
    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    @Basic
    @Column(name = "model_department", nullable = true, length = 40)
    public String getModelDepartment() {
        return modelDepartment;
    }

    public void setModelDepartment(String modelDepartment) {
        this.modelDepartment = modelDepartment;
    }

    @Basic
    @Column(name = "contact_information_isvisible", nullable = true)
    public Boolean getContactInformationIsvisible() {
        return contactInformationIsvisible;
    }

    public void setContactInformationIsvisible(Boolean contactInformationIsvisible) {
        this.contactInformationIsvisible = contactInformationIsvisible;
    }

    @Basic
    @Column(name = "model_permissions", nullable = true)
    public Integer getModelPermissions() {
        return modelPermissions;
    }

    public void setModelPermissions(Integer modelPermissions) {
        this.modelPermissions = modelPermissions;
    }

    @Basic
    @Column(name = "model_label", nullable = true, length = 200)
    public String getModelLabel() {
        return modelLabel;
    }

    public void setModelLabel(String modelLabel) {
        this.modelLabel = modelLabel;
    }

    @Basic
    @Column(name = "pv", nullable = true)
    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    @Basic
    @Column(name = "download_count", nullable = true)
    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Basic
    @Column(name = "model_file_id", nullable = true)
    public Long getModelFileId() {
        return modelFileId;
    }

    public void setModelFileId(Long modelFileId) {
        this.modelFileId = modelFileId;
    }

    @Basic
    @Column(name = "model_execute_type", nullable = true, length = 20)
    public String getModelExecuteType() {
        return modelExecuteType;
    }

    public void setModelExecuteType(String modelExecuteType) {
        this.modelExecuteType = modelExecuteType;
    }

    @Basic
    @Column(name = "model_developer_platform", nullable = true, length = 30)
    public String getModelDeveloperPlatform() {
        return modelDeveloperPlatform;
    }

    public void setModelDeveloperPlatform(String modelDeveloperPlatform) {
        this.modelDeveloperPlatform = modelDeveloperPlatform;
    }

    @Basic
    @Column(name = "model_runtime_platform", nullable = true, length = 30)
    public String getModelRuntimePlatform() {
        return modelRuntimePlatform;
    }

    public void setModelRuntimePlatform(String modelRuntimePlatform) {
        this.modelRuntimePlatform = modelRuntimePlatform;
    }

    @Basic
    @Column(name = "model_manual_id", nullable = true)
    public Long getModelManualId() {
        return modelManualId;
    }

    public void setModelManualId(Long modelManualId) {
        this.modelManualId = modelManualId;
    }

    @Basic
    @Column(name = "model_test_report_id", nullable = true)
    public Long getModelTestReportId() {
        return modelTestReportId;
    }

    public void setModelTestReportId(Long modelTestReportId) {
        this.modelTestReportId = modelTestReportId;
    }

    @Basic
    @Column(name = "model_assessment_report_id", nullable = true)
    public Long getModelAssessmentReportId() {
        return modelAssessmentReportId;
    }

    public void setModelAssessmentReportId(Long modelAssessmentReportId) {
        this.modelAssessmentReportId = modelAssessmentReportId;
    }

    @Basic
    @Column(name = "model_status", nullable = true)
    public Integer getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(Integer modelStatus) {
        this.modelStatus = modelStatus;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time", nullable = false)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "update_user", nullable = true, length = 20)
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    @Column(name = "update_user_id", nullable = true)
    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 400)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelInfo modelInfo = (ModelInfo) o;
        return id == modelInfo.id &&
                Objects.equals(modelName, modelInfo.modelName) &&
                Objects.equals(developerId, modelInfo.developerId) &&
                Objects.equals(contactInformation, modelInfo.contactInformation) &&
                Objects.equals(category, modelInfo.category) &&
                Objects.equals(type, modelInfo.type) &&
                Objects.equals(developer, modelInfo.developer) &&
                Objects.equals(writeTime, modelInfo.writeTime) &&
                Objects.equals(modelDepartment, modelInfo.modelDepartment) &&
                Objects.equals(contactInformationIsvisible, modelInfo.contactInformationIsvisible) &&
                Objects.equals(modelPermissions, modelInfo.modelPermissions) &&
                Objects.equals(modelLabel, modelInfo.modelLabel) &&
                Objects.equals(pv, modelInfo.pv) &&
                Objects.equals(downloadCount, modelInfo.downloadCount) &&
                Objects.equals(modelFileId, modelInfo.modelFileId) &&
                Objects.equals(modelExecuteType, modelInfo.modelExecuteType) &&
                Objects.equals(modelDeveloperPlatform, modelInfo.modelDeveloperPlatform) &&
                Objects.equals(modelRuntimePlatform, modelInfo.modelRuntimePlatform) &&
                Objects.equals(modelManualId, modelInfo.modelManualId) &&
                Objects.equals(modelTestReportId, modelInfo.modelTestReportId) &&
                Objects.equals(modelAssessmentReportId, modelInfo.modelAssessmentReportId) &&
                Objects.equals(modelStatus, modelInfo.modelStatus) &&
                Objects.equals(createTime, modelInfo.createTime) &&
                Objects.equals(updateTime, modelInfo.updateTime) &&
                Objects.equals(updateUser, modelInfo.updateUser) &&
                Objects.equals(updateUserId, modelInfo.updateUserId) &&
                Objects.equals(description, modelInfo.description);
    }

    @Transient
    public List<ModelUser> getTesters() {
        return testers;
    }

    @Transient
    public void setTesters(List<ModelUser> testers) {
        this.testers = testers;
    }

    @Transient
    public List<ModelUser> getEvaluators() {
        return evaluators;
    }

    @Transient
    public void setEvaluators(List<ModelUser> evaluators) {
        this.evaluators = evaluators;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelName, developerId, contactInformation, category, type, developer, writeTime, modelDepartment, contactInformationIsvisible, modelPermissions, modelLabel, pv, downloadCount, modelFileId, modelExecuteType, modelDeveloperPlatform, modelRuntimePlatform, modelManualId, modelTestReportId, modelAssessmentReportId, modelStatus, createTime, updateTime, updateUser, updateUserId, description);
    }
}
