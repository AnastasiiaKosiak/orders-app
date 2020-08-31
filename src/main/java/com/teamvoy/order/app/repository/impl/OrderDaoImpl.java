package com.teamvoy.order.app.repository.impl;

import com.teamvoy.order.app.model.Order;
import com.teamvoy.order.app.repository.OrderDao;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImpl implements OrderDao {
    private static final int ORDER_EXPIRATION_TIME = 10;
    private final InfluxDB database;
    private final InfluxDBProperties properties;

    public OrderDaoImpl(@Qualifier("influxDB") InfluxDB database,
                        InfluxDBProperties properties) {
        this.database = database;
        this.properties = properties;
    }

    @Override
    public Order create(Order order) {
        database.enableBatch(1, 1, TimeUnit.MILLISECONDS);
        Point point = Point.measurement("order")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("id", order.getId())
                .addField("creationTIme", String.valueOf(LocalDateTime.now()))
                .addField("itemName", order.getItemName())
                .addField("totalPrice", order.getTotalPrice())
                .addField("quantity", order.getItemsQuantity())
                .build();
        database.write(point);
        database.disableBatch();
        return order;
    }

    @Override
    public List<Order> getAll() {
        String selectQuery = "SELECT * FROM \"order\"";
        QueryResult queryResult = database.query(new Query(selectQuery, properties.getDatabase()));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Order.class);
    }

    @Override
    public Order getById(Long id) {
        String selectQuery = "SELECT * FROM \"order\" WHERE \"id\" = " + id;
        QueryResult queryResult = database.query(new Query(selectQuery, properties.getDatabase()));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Order> orders = resultMapper.toPOJO(queryResult, Order.class);
        return orders.stream().findFirst().get();
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM \"order\" WHERE \"id\" = " + id;
        database.query(new Query(deleteQuery, properties.getDatabase()));
    }

    @Override
    public boolean isNotValid(Order order) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(order.getCreationTIme().plusMinutes(ORDER_EXPIRATION_TIME));
    }
}

