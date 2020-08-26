package com.teamvoy.order.app.repository.impl;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.repository.ItemDao;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ItemDaoImpl implements ItemDao {
    private final InfluxDB database;
    private static final String DB_NAME = "test";
    private static final Integer REPLICATION_FACTOR = 1;
    private static final String INTERVAL = "30d";
    private static final String DB_POLICY = "defaultPolicy";

    public ItemDaoImpl(@Qualifier("connectToDatabase") InfluxDB database) {
        this.database = database;
    }

    @Override
    public Item save(Item item) {
        database.createDatabase(DB_NAME);
        configureDatabase(database);
        Point point = Point.measurement("item")
                .addField("id", item.getId())
                .addField("price", item.getPrice())
                .addField("quantity", item.getQuantity())
                .addField("itemName", item.getItemName())
                .build();
        database.write(DB_NAME, DB_POLICY, point);
        database.disableBatch();
        return item;
    }

    @Override
    public List<Item> saveAll(List<Item> items) {
        database.createDatabase(DB_NAME);
        configureDatabase(database);
        database.enableBatch(items.size(), items.size(), TimeUnit.MILLISECONDS);
        for (Item item : items) {
            Point point = Point.measurement("item")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .addField("id", item.getId())
                    .addField("price", item.getPrice())
                    .addField("quantity", item.getQuantity())
                    .addField("itemName", item.getItemName())
                    .build();
            database.write(DB_NAME, DB_POLICY, point);
        }
        database.disableBatch();
        return items;
    }

    @Override
    public List<Item> findCheapItems(String name) {
        configureDatabase(database);
        String selectQuery = "SELECT * FROM item WHERE item.itemName =" + name + "ORDER BY item.price ASC";
        Query queryObject = new Query(selectQuery, DB_NAME);
        QueryResult queryResult = database.query(queryObject);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Item.class);
    }

    @Override
    public Item findFirstByItemName(String name) {
        configureDatabase(database);
        String selectQuery = "SELECT * FROM item WHERE item.itemName = " + name;
        Query queryObject = new Query(selectQuery, DB_NAME);
        QueryResult queryResult = database.query(queryObject);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Item.class).get(0);
    }

    @Override
    public List<Item> findAll() {
        configureDatabase(database);
        String selectQuery = "SELECT * FROM item";
        Query queryObject = new Query(selectQuery, DB_NAME);
        QueryResult queryResult = database.query(queryObject);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Item.class);
    }

    private void configureDatabase(InfluxDB db) {
        database.setDatabase(DB_NAME);
        database.createRetentionPolicy(DB_POLICY, DB_NAME, INTERVAL, REPLICATION_FACTOR, true);
        database.setRetentionPolicy(DB_POLICY);
    }
}
