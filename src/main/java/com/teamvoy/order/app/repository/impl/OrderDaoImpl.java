package com.teamvoy.order.app.repository.impl;

import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.repository.OrderDao;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao {
    private static final String DB_NAME = "test";
    private static final Integer REPLICATION_FACTOR = 1;
    private static final String INTERVAL = "30d";
    private static final String DB_POLICY = "defaultPolicy";
    private final InfluxDB database;

    public OrderDaoImpl(@Qualifier("connectToDatabase") InfluxDB database) {
        this.database = database;
    }

    @Override
    public Order create(Order order) {
        database.createDatabase(DB_NAME);
        configureDatabase(database);
        Point point = Point.measurement("item")
                .addField("id", order.getId())
                .addField("creationTIme", String.valueOf(LocalDateTime.now()))
                .addField("itemName", order.getItemName())
                .addField("totalPrice", order.getTotalPrice())
                .addField("quantity", order.getItemsQuantity())
                .build();
        database.write(DB_NAME, DB_POLICY, point);
        database.disableBatch();
        return order;
    }

    @Override
    public List<Order> getAll() {
        configureDatabase(database);
        String selectQuery = "SELECT * FROM order";
        Query queryObject = new Query(selectQuery, DB_NAME);
        QueryResult queryResult = database.query(queryObject);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Order.class);
    }

    @Override
    public Order getById(Long id) {
        configureDatabase(database);
        String selectQuery = "SELECT * FROM order WHERE order.id = "+ id;
        Query queryObject = new Query(selectQuery, DB_NAME);
        QueryResult queryResult = database.query(queryObject);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Order.class).get(0);
    }

    @Override
    public void delete(Long id) {
        configureDatabase(database);
        String deleteQuery = "DELETE FROM order WHERE order.id = " + id;
        Query queryObject = new Query(deleteQuery, DB_NAME);
        database.query(queryObject);
    }

    private void configureDatabase(InfluxDB db) {
        database.setDatabase(DB_NAME);
        database.createRetentionPolicy(DB_POLICY, DB_NAME, INTERVAL, REPLICATION_FACTOR, true);
        database.setRetentionPolicy(DB_POLICY);
    }
}
