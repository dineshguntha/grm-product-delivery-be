package com.grm.productDelivery.services.Interfaces;

import com.grm.productDelivery.dto.UserDto;

/**
 * @author timbernerslee
 */
public interface UserService {
    UserDto create(UserDto userDto) throws Exception;
}
