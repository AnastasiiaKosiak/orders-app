package com.teamvoy.order.app.repository;

import com.teamvoy.order.app.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select it FROM Item it where it.itemName = :name order by it.price asc")
    List<Item> findCheapItems(@Param("name") String name);

    Item findFirstByItemName(String name);
}
