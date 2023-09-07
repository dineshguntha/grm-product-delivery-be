package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author timbernerslee
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * @param fistName
     * @return
     */
    List<User> findByFirstName(String fistName);
}
