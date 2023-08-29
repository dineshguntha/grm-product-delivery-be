package com.grm.productDelivery.repo;

import com.grm.productDelivery.model.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<GroceryItem, String> {

    @Query("{name:'?0'}")
    public abstract GroceryItem findItemByName(String name);

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1}")
    public abstract List<GroceryItem> findAll(String category);

    public abstract long count();
}
