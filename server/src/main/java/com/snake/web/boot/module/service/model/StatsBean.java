package com.snake.web.boot.module.service.model;

import javax.persistence.*;

@Table(name="stats_bean")
public class StatsBean {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public String id;
    public String stats_time;
    public String total_service_count;
    public String deployed_service_count;
    public String running_service_count;
    public String running_service_instance_count;
    public String host_count;
    public String used_host_count;
    public String total_call_count;
    public String up_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStats_time() {
        return stats_time;
    }

    public void setStats_time(String stats_time) {
        this.stats_time = stats_time;
    }

    public String getTotal_service_count() {
        return total_service_count;
    }

    public void setTotal_service_count(String total_service_count) {
        this.total_service_count = total_service_count;
    }

    public String getDeployed_service_count() {
        return deployed_service_count;
    }

    public void setDeployed_service_count(String deployed_service_count) {
        this.deployed_service_count = deployed_service_count;
    }

    public String getRunning_service_count() {
        return running_service_count;
    }

    public void setRunning_service_count(String running_service_count) {
        this.running_service_count = running_service_count;
    }

    public String getRunning_service_instance_count() {
        return running_service_instance_count;
    }

    public void setRunning_service_instance_count(String running_service_instance_count) {
        this.running_service_instance_count = running_service_instance_count;
    }

    public String getHost_count() {
        return host_count;
    }

    public void setHost_count(String host_count) {
        this.host_count = host_count;
    }

    public String getUsed_host_count() {
        return used_host_count;
    }

    public void setUsed_host_count(String used_host_count) {
        this.used_host_count = used_host_count;
    }

    public String getTotal_call_count() {
        return total_call_count;
    }

    public void setTotal_call_count(String total_call_count) {
        this.total_call_count = total_call_count;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

}
