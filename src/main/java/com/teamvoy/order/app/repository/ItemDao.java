package com.teamvoy.order.app.repository;

import com.teamvoy.order.app.model.Item;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface ItemDao {

    Item save(Item item);

    List<Item> saveAll(List<Item> items);

    List<Item> findCheapItems(@Param("name") String name);

    Item findFirstByItemName(String name);

    List<Item> findAll();

}
