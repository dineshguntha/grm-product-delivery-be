package com.grm.productDelivery.validator;

import com.grm.productDelivery.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author timbernerslee
 */
@Slf4j
@Component
public class UserValidator implements Validator {

    /**
     * @param clazz ß
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    /**
     * @param target
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        log.info("inside UserValidator.validate() Begins");
        UserDto userDto = (UserDto) target;
        if (userDto.getFirstName() == null || userDto.getFirstName().length() == 0) {
            errors.rejectValue("firstName", "error.firstName.required", null, "First Name Required");
            log.error("First Name Required");
        }
        if (userDto.getLastName() == null || userDto.getLastName().length() == 0) {
            errors.rejectValue("lastName", "error.lastName.required", null, "Last Name Required");
            log.error("Last Name Required");
        }
        if (userDto.getPassword() == null || userDto.getPassword().length() == 0) {
            errors.rejectValue("password", "error.password.required", null, "Password Required");
            log.error("Password Required");
        }
        if (userDto.getPhoneNumber() == null || userDto.getPhoneNumber().length() == 0) {
            errors.rejectValue("phoneNumber", "error.phoneNumber.required", null, "Phone Number Required");
            log.error("Phone Number Required");
        }
        if (userDto.getRoles() == null || userDto.getRoles().size() == 0) {
            errors.rejectValue("roles", "error.roles.required", null, "Role Required");
            log.error("Role Required");
        }
        if (userDto.getEntityName() == null || userDto.getEntityName().length() == 0) {
            errors.rejectValue("entityName", "error.entityName.required", null, "Entity Name Required");
            log.error("Entity Name Required");
        }
        log.info("inside UserValidator.validate() End's");
    }

    /**
     * @param target
     * @return
     */
    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
