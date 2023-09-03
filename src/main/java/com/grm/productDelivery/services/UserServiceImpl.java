package com.grm.productDelivery.services;

import com.grm.productDelivery.dao.UserDao;
import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.models.User;
import com.grm.productDelivery.services.Interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author timbernerslee
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto create(UserDto userDto) throws Exception {
        log.info("inside UserServiceImpl.create() Begins");
        User user = userDao.create(userDto);
        userDto.setUserId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setPassword(user.getPassword());
        userDto.setEmailId(user.getEmailId());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRoles(user.getRoles());
        userDto.setEntityName(user.getEntityName());
        userDto.setRouteName(user.getRouteName());
        log.info("inside UserServiceImpl.create() End's");
        return userDto;
    }
}
