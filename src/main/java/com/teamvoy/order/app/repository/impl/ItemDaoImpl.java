package com.teamvoy.order.app.repository.impl;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.repository.ItemDao;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ItemDaoImpl implements ItemDao {
    private static final String DB_NAME = "test";
    private static final String DB_POLICY = "defaultPolicy";
    private final InfluxDB database;

    public ItemDaoImpl(@Qualifier("getInfluxDatabase") InfluxDB database) {
        this.database = database;
    }

    @Override
    public Item save(Item item) {
        Point point = Point.measurement("item")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
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
        String selectQuery = "SELECT * FROM item WHERE item.itemName ="
                + name + "ORDER BY item.price ASC";
        Query queryObject = new Query(selectQuery, DB_NAME);
        QueryResult queryResult = database.query(queryObject);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Item.class);
    }

    @Override
    public Item findFirstByItemName(String name) {
        String selectQuery = "SELECT * FROM item WHERE item.itemName = " + name;
        Query queryObject = new Query(selectQuery, DB_NAME);
        QueryResult queryResult = database.query(queryObject);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Item.class).get(0);
    }

    @Override
    public List<Item> findAll() {
        String selectQuery = "SELECT * FROM item";
        Query queryObject = new Query(selectQuery, DB_NAME);
        QueryResult queryResult = database.query(queryObject);
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Item.class);
    }
}
