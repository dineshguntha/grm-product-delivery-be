package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.Entity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface EntityRepository extends MongoRepository<Entity, String> {

    List<Entity> findByName(String name);

}
