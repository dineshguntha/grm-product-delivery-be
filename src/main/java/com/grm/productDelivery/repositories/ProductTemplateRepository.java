package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.Entity;
import com.grm.productDelivery.models.ProductTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductTemplateRepository extends MongoRepository<ProductTemplate, String> {
}
