package com.grm.productDelivery.services;

import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.models.User;
import com.grm.productDelivery.repositories.UserRepository;
import com.grm.productDelivery.services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author timbernerslee
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(UserDto userDto) {
        User saveNewUser = new User();
        saveNewUser.setFirstName(userDto.getFirstName());
        saveNewUser.setMiddleName(userDto.getMiddleName());
        saveNewUser.setLastName(userDto.getLastName());
        saveNewUser.setPassword(userDto.getPassword());
        saveNewUser.setEmailId(userDto.getEmailId());
        saveNewUser.setPhoneNumber(userDto.getPhoneNumber());
        saveNewUser.setRoles(userDto.getRoles());
        saveNewUser.setEntityName(userDto.getEntityName());
        saveNewUser.setRouteName(userDto.getRouteName());
        return userRepository.save(saveNewUser);
    }
}
