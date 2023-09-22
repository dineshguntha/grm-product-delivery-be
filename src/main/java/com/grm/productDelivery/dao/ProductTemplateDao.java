package com.grm.productDelivery.dao;

import com.grm.productDelivery.format.RequestFormat;
import com.grm.productDelivery.models.ProductTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class ProductTemplateDao extends RequestFormat {

    @Autowired
    private MongoTemplate mongoTemplate;

    public ProductTemplate createProdTemp(ProductTemplate data) {
        return mongoTemplate.save(data);
    }

}
