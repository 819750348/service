package com.snake.web.boot.module.interfaces.model;

import javax.persistence.*;

@Table(name = "restful_bean")
public class RestfulBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public String id;
    public String name;
    public String status;
    public String node;
    public String serviceId;
    public String instanceCount;
    public String minInstanceCount;
    public String maxInstanceCount;
    public String deployedOnNodes;
    public String runningOnNodes;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(String instanceCount) {
        this.instanceCount = instanceCount;
    }

    public String getMinInstanceCount() {
        return minInstanceCount;
    }

    public void setMinInstanceCount(String minInstanceCount) {
        this.minInstanceCount = minInstanceCount;
    }

    public String getMaxInstanceCount() {
        return maxInstanceCount;
    }

    public void setMaxInstanceCount(String maxInstanceCount) {
        this.maxInstanceCount = maxInstanceCount;
    }

    public String getDeployedOnNodes() {
        return deployedOnNodes;
    }

    public void setDeployedOnNodes(String deployedOnNodes) {
        this.deployedOnNodes = deployedOnNodes;
    }

    public String getRunningOnNodes() {
        return runningOnNodes;
    }

    public void setRunningOnNodes(String runningOnNodes) {
        this.runningOnNodes = runningOnNodes;
    }


}
