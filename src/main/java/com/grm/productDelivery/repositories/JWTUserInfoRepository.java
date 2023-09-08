package com.grm.productDelivery.repositories;

import com.grm.productDelivery.models.Entity;
import com.grm.productDelivery.models.JWTUserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JWTUserInfoRepository extends MongoRepository<JWTUserInfo, String> {
    Optional<JWTUserInfo> findByName(String username);
}
