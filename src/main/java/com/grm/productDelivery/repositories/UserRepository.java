package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * @param id
     * @return
     */
    @Override
    Optional<User> findById(String id);

    /**
     * @param entityName
     * @return
     */
    List<User> findByEntityName(String entityName);


    /**
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);

}
