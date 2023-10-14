package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author timbernerslee
 */
@Repository
public interface CustomerTemplateRepository extends MongoRepository<CustomerTemplate, String> {

    /**
     * @param routeName
     * @return List of CustomerTemplate
     */
    List<CustomerTemplate> findByRouteName(String routeName);
}
