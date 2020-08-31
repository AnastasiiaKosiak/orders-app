package com.teamvoy.order.app.repository.impl;

import com.teamvoy.order.app.model.Item;
import com.teamvoy.order.app.repository.ItemDao;
import java.util.Comparator;
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
public class ItemDaoImpl implements ItemDao {
    private final InfluxDB database;
    private final InfluxDBProperties properties;

    public ItemDaoImpl(@Qualifier("influxDB") InfluxDB database,
                       InfluxDBProperties properties1) {
        this.database = database;
        this.properties = properties1;
    }

    @Override
    public Item save(Item item) {
        database.enableBatch(1, 1, TimeUnit.MILLISECONDS);
        Point point = Point.measurement("item")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("id", item.getId())
                .addField("price", item.getPrice())
                .addField("quantity", item.getQuantity())
                .addField("itemName", item.getItemName())
                .build();
        database.write(point);
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
            database.write(point);
        }
        database.disableBatch();
        return items;
    }

    @Override
    public List<Item> findCheapItems(String name) {
        String selectQuery = "SELECT * FROM \"item\" WHERE \"itemName\" = '" + name + "'";
        QueryResult queryResult = database.query(new Query(selectQuery,
                properties.getDatabase()));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Item> result = resultMapper.toPOJO(queryResult, Item.class);
        result.sort(Comparator.comparing(Item::getPrice));
        return result;
    }

    @Override
    public Item findFirstByItemName(String name) {
        String selectQuery = "SELECT * FROM \"item\" WHERE \"itemName\" = '" + name + "'";
        QueryResult queryResult = database.query(new Query(selectQuery,
                properties.getDatabase()));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        List<Item> allItems = resultMapper.toPOJO(queryResult, Item.class);
        return allItems.get(0);
    }

    @Override
    public List<Item> findAll() {
        String selectQuery = "SELECT * FROM \"item\"";
        QueryResult queryResult = database.query(new Query(selectQuery,
                properties.getDatabase()));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Item.class);
    }
}
