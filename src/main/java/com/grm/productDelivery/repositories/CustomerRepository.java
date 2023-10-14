package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

/**
 * @author timbernerslee
 */
@Repository
public interface CustomerRepository extends MongoRepository<CustomerModel, String> {
}
