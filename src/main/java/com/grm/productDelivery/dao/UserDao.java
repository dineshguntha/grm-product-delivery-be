package com.grm.productDelivery.dao;

import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.exceptions.ResourceNotFoundException;
import com.grm.productDelivery.models.User;
import com.grm.productDelivery.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author timbernerslee
 */
@Slf4j
@Component
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param userDto
     * @return
     * @throws Exception
     */
    public User create(UserDto userDto) throws Exception {
        log.info("inside UserDao.create() Begins");
        User saveNewUser = new User();
        saveNewUser.setId(UUID.randomUUID().toString().split("-")[0]);
        saveNewUser.setFirstName(userDto.getFirstName());
        saveNewUser.setMiddleName(userDto.getMiddleName());
        saveNewUser.setLoginName(userDto.getLoginName());
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

    /**
     * @param firstName
     * @return
     */
    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    /**
     * @param id
     * @return
     */
    public User getUserById(String id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    }

    /**
     * @param user
     * @return
     */
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @param id
     * @throws ResourceNotFoundException
     */
    public void deleteUser(String id) throws ResourceNotFoundException {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        userRepository.delete(existingUser);
    }

    /**
     * @param entityName
     * @return
     */
    public List<User> getUsersByEntityName(String entityName) {
        return userRepository.findByEntityName(entityName);
    }

    /**
     * @param loginName
     * @return
     */
    public User getUserByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

}
