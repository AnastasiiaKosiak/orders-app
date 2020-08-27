package com.teamvoy.order.app.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDatabaseConfig {

    @Bean
    public InfluxDB getInfluxDatabase(InfluxDatabaseProperties properties) {
        InfluxDB influxDB = InfluxDBFactory.connect(properties.getDbUrl(),
                properties.getUserName(), properties.getUserPassword());
        influxDB.createDatabase(properties.getDbName());
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        influxDB.createRetentionPolicy(properties.getDbPolicy(),
                properties.getDbName(),
                properties.getInterval(),
                properties.getReplicationFactor(),
                true);
        influxDB.setRetentionPolicy(properties.getDbPolicy());
        influxDB.setDatabase(properties.getDbName());
        return influxDB;
    }
}
