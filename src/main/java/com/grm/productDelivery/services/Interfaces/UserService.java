package com.grm.productDelivery.services.Interfaces;

import com.grm.productDelivery.dto.UserDto;
import com.grm.productDelivery.models.User;

/**
 * @author timbernerslee
 */
public interface UserService {
    User create(UserDto userDto);
}
