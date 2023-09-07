package com.grm.productDelivery.services.Interfaces;

import com.grm.productDelivery.dto.UserDto;

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
}
