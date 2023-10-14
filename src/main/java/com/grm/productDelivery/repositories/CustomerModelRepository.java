package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author timbernerslee
 */
@Repository
public interface CustomerModelRepository extends MongoRepository<CustomerModel, String> {

    /**
     * @param routeName
     * @return List of CustomerModel
     */
    List<CustomerModel> findByRouteName(String routeName);
}
