package com.teamvoy.order.app.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDatabaseConfig {
    private static final String DATABASE_URL = "http://127.0.0.1:8086";
    private static final String USER_NAME = "admin";
    private static final String USER_PASSWORD = "admin";

    @Bean
    public InfluxDB connectToDatabase() {
        return InfluxDBFactory.connect(DATABASE_URL, USER_NAME, USER_PASSWORD);
    }
}
