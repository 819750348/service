package com.snake.web.boot.module.rup.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "data_permission")
public class DataPermission {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;


    /**
     * 权限类型 A,B,C,D
     */
    @Column(name = "permission_type")
    @Getter
    @Setter
    private String permissionType;

    /**
     * 资源名称
     */
    @Column(name = "resources_name")
    @Getter
    @Setter
    private  Boolean resourcesName ;

    /**
     * 开发人员信息
     */
    @Column(name = "developer_information")
    @Getter
    @Setter
    private  Boolean developerInformation ;


    /**
     * 查看模型描述信息
     */
    @Column(name = "view_model_description")
    @Getter
    @Setter
    private  Boolean viewModelDescription ;


    /**
     * 下载模型描述文件
     */
    @Column(name = "download_model_description_file")
    @Getter
    @Setter
    private  Boolean downloadModelDescriptionFile ;


    /**
     * 下载模型文件
     */
    @Column(name = "downLoad_model_file")
    @Getter
    @Setter
    private  Boolean downLoadModelFile ;


    /**
     * 查看模型用户信息
     */
    @Column(name = "view_model_users")
    @Getter
    @Setter
    private  Boolean viewModelUsers ;


    /**
     * 查看参数描述
     */
    @Column(name = "view_parameter_description")
    @Getter
    @Setter
    private  Boolean viewParameterDescription ;

    /**
     * 下载参数描述文件
     */
    @Column(name = "download_parameter_file")
    @Getter
    @Setter
    private  Boolean downloadParameterFile ;

}
