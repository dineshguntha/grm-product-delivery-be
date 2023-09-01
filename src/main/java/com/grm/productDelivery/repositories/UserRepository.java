package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author timbernerslee
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
