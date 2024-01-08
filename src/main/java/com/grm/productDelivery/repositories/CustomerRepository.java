package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author timbernerslee
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    /**
     * @param id
     * @return Customer Object
     */
    Optional<Customer> findById(String id);

    /**
     * @param name
     * @return List of Customer
     */
    List<Customer> findByName(String name);

    /**
     * @param mobile
     * @return List of Customer
     */
    List<Customer> findByMobile(String mobile);

    /**
     * @param email
     * @return List of Customer
     */
    List<Customer> findByEmail(String email);

}
