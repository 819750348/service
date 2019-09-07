package com.snake.web.boot.module.rup.model;

import javax.persistence.*;

@Table(name = "service_info")
public class ServiceInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;

    @Column(name = "category_id")
    public long categoryId;

    @Column(name = "name")
    public String name;

    @Column(name = "service_label")
    public String serviceLabel;

    @Column(name = "designer")
    public String designer;

    @Column(name = "designer_id")
    public long designerId;

    @Column(name = "designer_department")
    public String designerDepartment;

    @Column(name = "contact_information")
    public String contactInformation;

    @Column(name = "description")
    public String description;

    @Column(name = "interface_description_file_id")
    public long interfaceDescriptionFileId;

    @Column(name = "developer")
    public String developer;

    @Column(name = "developer_id")
    public long developerId;


    @Column(name = "designer_status")
    public String designerStatus;

    @Column(name = "create_time")
    public String createTime;

    @Column(name = "design_submission_time")
    public String designSubmissionTime;

    @Column(name = "design_submission_user")
    public String designSubmissionUser;

    @Column(name = "design_submission_user_id")
    public long designSubmissionUserId;

    @Column(name = "service_implementation_file_id")
    public long serviceImplementationFileId;

    @Column(name = "user_manual_file_id")
    public long userManualFileId;

    @Column(name = "demo_file_id")
    public long demoFileId;

    @Column(name = "min_instance")
    public int minInstance;

    @Column(name = "max_instance")
    public int maxInstance;

    @Column(name = "developer_submission_time")
    public String developerSubmissionTime;

    @Column(name = "developer_submission_user")
    public String developerSubmissionUser;

    @Column(name = "developer_submission_user_id")
    public long developerSubmissionUserId;

    @Column(name = "developer_status")
    public int developerStatus;

    @Column(name = "deploy_submission_time")
    public String deploySubmissionTime;

    @Column(name = "deploy_submission_user")
    public String deploySubmissionUser;

    @Column(name = "deploy_submission_user_id")
    public long  deploySubmissionUserId;

    @Column(name = "deploy_status")
    public int  deployStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceLabel() {
        return serviceLabel;
    }

    public void setServiceLabel(String serviceLabel) {
        this.serviceLabel = serviceLabel;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public long getDesignerId() {
        return designerId;
    }

    public void setDesignerId(long designerId) {
        this.designerId = designerId;
    }

    public String getDesigner_department() {
        return designerDepartment;
    }

    public void setDesigner_department(String designer_department) {
        this.designerDepartment = designer_department;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getInterfaceDescriptionFileId() {
        return interfaceDescriptionFileId;
    }

    public void setInterfaceDescriptionFileId(long interfaceDescriptionFileId) {
        this.interfaceDescriptionFileId = interfaceDescriptionFileId;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDesignSubmissionTime() {
        return designSubmissionTime;
    }

    public void setDesignSubmissionTime(String designSubmissionTime) {
        this.designSubmissionTime = designSubmissionTime;
    }

    public String getDesignSubmissionUser() {
        return designSubmissionUser;
    }

    public void setDesignSubmissionUser(String designSubmissionUser) {
        this.designSubmissionUser = designSubmissionUser;
    }

    public long getDesignSubmissionUserId() {
        return designSubmissionUserId;
    }

    public void setDesignSubmissionUserId(long designSubmissionUserId) {
        this.designSubmissionUserId = designSubmissionUserId;
    }

    public long getServiceImplementationFileId() {
        return serviceImplementationFileId;
    }

    public void setServiceImplementtationFileId(long serviceImplementationFileId) {
        this.serviceImplementationFileId = serviceImplementationFileId;
    }

    public long getUserManualFileId() {
        return userManualFileId;
    }

    public void setUserManualFileId(long userManualFileId) {
        this.userManualFileId = userManualFileId;
    }

    public long getDemoFileId() {
        return demoFileId;
    }

    public void setDemoFileId(long demoFileId) {
        this.demoFileId = demoFileId;
    }

    public int getMinInstance() {
        return minInstance;
    }

    public void setMinInstance(int minInstance) {
        this.minInstance = minInstance;
    }

    public int getMaxInstance() {
        return maxInstance;
    }

    public void setMaxInstance(int maxInstance) {
        this.maxInstance = maxInstance;
    }

    public String getDeveloperSubmissionTime() {
        return developerSubmissionTime;
    }

    public void setDeveloperSubmissionTime(String developerSubmissionTime) {
        this.developerSubmissionTime = developerSubmissionTime;
    }

    public String getDeveloperSubmissionUser() {
        return developerSubmissionUser;
    }

    public void setDeveloperSubmissionUser(String developerSubmissionUser) {
        this.developerSubmissionUser = developerSubmissionUser;
    }

    public long getDeveloperSubmissionUserId() {
        return developerSubmissionUserId;
    }

    public void setDeveloperSubmissionUserId(long developerSubmissionUserId) {
        this.developerSubmissionUserId = developerSubmissionUserId;
    }

    public int getDeveloperStatus() {
        return developerStatus;
    }

    public void setDeveloperStatus(int developerStatus) {
        this.developerStatus = developerStatus;
    }

    public String getDeploySubmissionTime() {
        return deploySubmissionTime;
    }

    public void setDeploySubmissionTime(String deploySubmissionTime) {
        this.deploySubmissionTime = deploySubmissionTime;
    }

    public String getDeploySubmissionUser() {
        return deploySubmissionUser;
    }

    public void setDeploySubmissionUser(String deploySubmissionUser) {
        this.deploySubmissionUser = deploySubmissionUser;
    }

    public long getDeploySubmissionUserId() {
        return deploySubmissionUserId;
    }

    public void setDeploySubmissionUserId(long deploySubmissionUserId) {
        this.deploySubmissionUserId = deploySubmissionUserId;
    }

    public int getDeployStatus() {
        return deployStatus;
    }

    public void setDeployStatus(int deployStatus) {
        this.deployStatus = deployStatus;
    }

    public String getDesignerStatus() {
        return designerStatus;
    }

    public void setDesignerStatus(String designerStatus) {
        this.designerStatus = designerStatus;
    }



}
