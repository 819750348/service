package com.snake.web.boot.module.rup.model;


import com.snake.web.boot.module.system.model.Attachment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "base_param")
public class BasicParam {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;


    /**
     * 基本信息
     */

    @Column(name = "is_basic_info")
    @Getter
    @Setter
    private  Boolean isBasicInfo ;

    /**
     * 描述信息
     */
    @Column(name = "is_description")
    @Getter
    @Setter
    private  Boolean isDescription ;


    /**
     * 第三方测试
     */
    @Column(name = "is_test")
    @Getter
    @Setter
    private  Boolean isTest ;


    /**
     * 第三方测试数量
     */
    @Column(name = "test_number")
    @Getter
    @Setter
    private  Integer  testNumber ;

    /**
     * 第三方评估
     */
    @Column(name = "is_evaluation")
    @Getter
    @Setter
    private  Boolean isEvaluation ;


    /**
     * 第三方评估数量
     */
    @Column(name = "evaluation_number")
    @Getter
    @Setter
    private  Integer evaluationNumber ;


    /**
     * 资源下载设置，
     * 0: 申请人-->上传者
     * 1: 申请人-->管理员
     * 2: 申请人-->上传者-->管理员
     */
    @Column(name = "resource_download_set")
    @Getter
    @Setter
    private  Integer resourceDownloadSet ;

    /**
     *模型文件xml
     */
    @Column(name = "model_file_xml_id")
    @Getter
    @Setter
    private  Long  modelFileXmlId ;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "model_file_xml_id", referencedColumnName = "id",insertable = false, updatable = false)
    @Setter
    @Getter
    private Attachment modelFileXml;

    /**
     * 模型文件模板
     */
    @Column(name = "model_file_template_id")
    @Getter
    @Setter
    private  Long  modelFileTemplateId ;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "model_file_template_id", referencedColumnName = "id",insertable = false, updatable = false)
    @Setter
    @Getter
    private Attachment modelFileTemplate;//


    /**
     * 测试报告xml
     */
    @Column(name = "test_report_xml_id")
    @Getter
    @Setter
    private  Long  testReportXmlId ;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "test_report_xml_id", referencedColumnName = "id",insertable = false, updatable = false)
    @Setter
    @Getter
    private Attachment testReportXml;//

    /**
     * 测试报告模板
     */
    @Column(name = "test_report_template_id")
    @Getter
    @Setter
    private  Long  testReportTemplateId ;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "test_report_template_id", referencedColumnName = "id",insertable = false, updatable = false)
    @Setter
    @Getter
    private Attachment testReportTemplate;//


    /**
     * 使用手册xml
     */
    @Column(name = "manual_xml_id")
    @Getter
    @Setter
    private  Long  manualXmlId ;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "manual_xml_id", referencedColumnName = "id",insertable = false, updatable = false)
    @Setter
    @Getter
    private Attachment manualXml;//

    /**
     * 使用手册模板
     */
    @Column(name = "manual_template_id")
    @Getter
    @Setter
    private  Long  manualTemplateId ;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "manual_template_id", referencedColumnName = "id",insertable = false, updatable = false)
    @Setter
    @Getter
    private Attachment manualTemplate;//

    /**
     * 可信度报告xml
     */
    @Column(name = "assessment_report_xml_id")
    @Getter
    @Setter
    private  Long  assessmentReportXmlId ;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "assessment_report_xml_id", referencedColumnName = "id",insertable = false, updatable = false)
    @Setter
    @Getter
    private Attachment assessmentReportXml;//

    /**
     * 可信度报告模板
     */
    @Column(name = "assessment_report_template_id")
    @Getter
    @Setter
    private  Long  assessmentReportTemplateId ;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "assessment_report_template_id", referencedColumnName = "id",insertable = false, updatable = false)
    @Setter
    @Getter
    private Attachment assessmentReportTemplate;//
}
