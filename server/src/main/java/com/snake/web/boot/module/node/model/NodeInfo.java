package com.snake.web.boot.module.node.model;

import javax.persistence.*;

@Table(name="node_info")
public class NodeInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public String id;
    public String host_name;
    public String ip;
    public String cpu_count;
    public String thread_count;
    public String cpu_frequency;
    public String memory_size;
    public String disk_size;
    public String deployed_services;
    public String running_services;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCpu_count() {
        return cpu_count;
    }

    public void setCpu_count(String cpu_count) {
        this.cpu_count = cpu_count;
    }

    public String getThread_count() {
        return thread_count;
    }

    public void setThread_count(String thread_count) {
        this.thread_count = thread_count;
    }

    public String getCpu_frequency() {
        return cpu_frequency;
    }

    public void setCpu_frequency(String cpu_frequency) {
        this.cpu_frequency = cpu_frequency;
    }

    public String getMemory_size() {
        return memory_size;
    }

    public void setMemory_size(String memory_size) {
        this.memory_size = memory_size;
    }

    public String getDisk_size() {
        return disk_size;
    }

    public void setDisk_size(String disk_size) {
        this.disk_size = disk_size;
    }

    public String getDeployed_services() {
        return deployed_services;
    }

    public void setDeployed_services(String deployed_services) {
        this.deployed_services = deployed_services;
    }

    public String getRunning_services() {
        return running_services;
    }

    public void setRunning_services(String running_services) {
        this.running_services = running_services;
    }

    }
