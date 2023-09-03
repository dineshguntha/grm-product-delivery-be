package com.grm.productDelivery.dao;

import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.models.User;
import com.grm.productDelivery.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    public User create(UserDto userDto) throws Exception {
        log.info("inside UserDao.create() Begins");
        User saveNewUser = new User();
        saveNewUser.setId(UUID.randomUUID().toString().split("-")[0]);
        saveNewUser.setFirstName(userDto.getFirstName());
        saveNewUser.setMiddleName(userDto.getMiddleName());
        saveNewUser.setLastName(userDto.getLastName());
        saveNewUser.setPassword(userDto.getPassword());
        saveNewUser.setEmailId(userDto.getEmailId());
        saveNewUser.setPhoneNumber(userDto.getPhoneNumber());
        saveNewUser.setRoles(userDto.getRoles());
        saveNewUser.setEntityName(userDto.getEntityName());
        saveNewUser.setRouteName(userDto.getRouteName());
        List<User> existUsers = getUserByFirstName(userDto.getFirstName());
        if (existUsers.size() >= 1) {
            throw new Exception("User is already exist");
        } else {
            userRepository.save(saveNewUser);
        }
        log.info("inside UserDao.create() End's");
        return saveNewUser;
    }

    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

}
