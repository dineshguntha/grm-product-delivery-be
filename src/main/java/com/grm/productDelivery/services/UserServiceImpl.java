package com.grm.productDelivery.services;

import com.grm.productDelivery.dao.UserDao;
import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.exceptions.ResourceNotFoundException;
import com.grm.productDelivery.models.User;
import com.grm.productDelivery.services.Interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author timbernerslee
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param userDto ß
     * @return
     * @throws Exception
     */
    @Override
    public UserDto create(UserDto userDto) throws Exception {
        log.info("inside UserServiceImpl.create() Begins");
        User user = userDao.create(userDto);
        log.info("inside UserServiceImpl.create() End's");
        return this.modelMapper.map(user, UserDto.class);
    }


    /**
     * @param id
     * @param userDto
     * @return
     * @throws ResourceNotFoundException
     */
    @Override
    public UserDto updateUser(String id, UserDto userDto) throws ResourceNotFoundException {
        log.info("inside UserServiceImpl.updateUser() Begins");
        User existingUser = userDao.getUserById(id);
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setMiddleName(userDto.getMiddleName());
        existingUser.setLoginName(userDto.getLoginName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setEmailId(userDto.getEmailId());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());
        existingUser.setRoles(userDto.getRoles());
        existingUser.setEntityName(userDto.getEntityName());
        existingUser.setRouteName(userDto.getRouteName());
        userDao.updateUser(existingUser);
        userDto.setUserId(existingUser.getId());
        log.info("inside UserServiceImpl.updateUser() End's");
        return userDto;
    }


    /**
     * @param id
     * @throws ResourceNotFoundException
     */
    @Override
    public void deleteUser(String id) throws ResourceNotFoundException {
        log.info("inside UserServiceImpl.deleteUser() Begins");
        userDao.deleteUser(id);
        log.info("inside UserServiceImpl.deleteUser() End's");
    }

    /**
     * @param entityName
     * @return
     */
    @Override
    public List<UserDto> getUsersListByEntityName(String entityName) {
        log.info("inside UserServiceImpl.getUsersListByEntityName()");
        List<User> entityNamesList = userDao.getUsersByEntityName(entityName);
        return entityNamesList
                .stream()
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }


    /**
     * @param loginName
     * @return
     */
    @Override
    public UserDto getUserByLoginName(String loginName) {
        log.info("inside UserServiceImpl.getUserByLoginName()");
        return this.modelMapper.map(userDao.getUserByLoginName(loginName), UserDto.class);
    }
}
