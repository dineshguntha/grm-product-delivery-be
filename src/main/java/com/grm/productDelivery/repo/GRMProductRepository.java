package com.grm.productDelivery.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.grm.productDelivery.model.GRMProductDelivery;

import java.util.List;


public interface GRMProductRepository extends MongoRepository<GRMProductDelivery, String> {

    List<GRMProductDelivery> findByName(String name);

}
