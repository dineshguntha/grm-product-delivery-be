package com.grm.productDelivery.services.Interfaces;

import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.exceptions.ResourceNotFoundException;

import java.util.List;

/**
 * @author timbernerslee
 */
public interface UserService {
    /**
     * @param userDto ß
     * @return
     * @throws Exception
     */
    UserDto create(UserDto userDto) throws Exception;


    /**
     * @param id
     * @param userDto
     * @return
     * @throws ResourceNotFoundException
     */
    UserDto updateUser(String id, UserDto userDto) throws ResourceNotFoundException;


    /**
     * @param id
     * @return
     */
    void deleteUser(String id) throws ResourceNotFoundException;

    /**
     * @param entityName
     * @return
     */
    List<UserDto> getUsersListByEntityName(String entityName);

    /**
     * @param loginName
     * @return
     */
    UserDto getUserByLoginName(String loginName);

}
