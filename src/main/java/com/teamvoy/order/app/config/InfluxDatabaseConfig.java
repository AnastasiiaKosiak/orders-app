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
    public InfluxDB influxDB(final InfluxDBProperties influxDatabaseProperties) {
        InfluxDB connection = InfluxDBFactory.connect(influxDatabaseProperties.getUrl(),
                influxDatabaseProperties.getUsername(),
                influxDatabaseProperties.getPassword());
        if (!connection.databaseExists(influxDatabaseProperties.getDatabase())) {
            connection.createDatabase(influxDatabaseProperties.getDatabase());
        }
        connection.setDatabase(influxDatabaseProperties.getDatabase());
        return connection;
    }
}
