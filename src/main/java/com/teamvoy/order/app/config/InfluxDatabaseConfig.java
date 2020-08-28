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
    public InfluxDB influxDB(final InfluxDBProperties influxDBProperties) {
        InfluxDB connection = InfluxDBFactory.connect(influxDBProperties.getUrl(),
                influxDBProperties.getUsername(),
                influxDBProperties.getPassword());
        if (!connection.databaseExists(influxDBProperties.getDatabase())) {
            connection.createDatabase(influxDBProperties.getDatabase());
        }
        connection.setDatabase(influxDBProperties.getDatabase());
        return connection;
    }
}
