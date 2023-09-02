package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.GRMProductDelivery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface EntityRepository extends MongoRepository<GRMProductDelivery, String> {

    List<GRMProductDelivery> findByName(String name);

}
