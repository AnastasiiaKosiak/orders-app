package com.teamvoy.order.app.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.influxdb.InfluxDBProperties;

@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxDatabaseConfig {
    @Bean
    public InfluxDB influxDB() {
        InfluxDB connection = InfluxDBFactory.connect("http://172.21.0.1:8086", "admin", "admin");
        connection.createDatabase("test");
        connection.setDatabase("test");
        return connection;
    }
}
