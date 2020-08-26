package com.teamvoy.order.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "influxdb")
@Component
public class InfluxDatabaseProperties {
    private String dbUrl = "http://127.0.0.1:8086";
    private String dbName = "test";
    private String userName = "admin";
    private String userPassword = "admin";
    private Integer replicationFactor = 1;
    private String interval = "30d";
    private String dbPolicy = "defaultPolicy";

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Integer replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getDbPolicy() {
        return dbPolicy;
    }

    public void setDbPolicy(String dbPolicy) {
        this.dbPolicy = dbPolicy;
    }
}
