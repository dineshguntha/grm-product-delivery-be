package com.grm.productDelivery.validator;

import com.grm.productDelivery.dto.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;

/**
 * @author timbernerslee
 */
@Slf4j
@Component
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerTemplateDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info("inside CustomerValidator.validate() Begins");
        CustomerTemplateDto customerTemplateDto = (CustomerTemplateDto) target;
        if (customerTemplateDto.getCreatedAt() == null) {
            errors.rejectValue("createdAt", "error.createdAt.required", null, "Created At Required");
            log.error("Created At Required");
        }
        if (customerTemplateDto.getCreatedBy() == null || customerTemplateDto.getCreatedBy().length() == 0) {
            errors.rejectValue("createdBy", "error.createdBy.required", null, "Created By Required");
            log.error("Created By Required");
        }
        if (customerTemplateDto.getModifiedAt() == null) {
            errors.rejectValue("modifiedAt", "error.modifiedAt.required", null, "Modified At Required");
            log.error("Modified At Required");
        }
        if (customerTemplateDto.getModifiedBy() == null || customerTemplateDto.getModifiedBy().length() == 0) {
            errors.rejectValue("modifiedBy", "error.modifiedBy.required", null, "Modified By Required");
            log.error("Modified By Required");
        }
        if (customerTemplateDto.getEntityName() == null || customerTemplateDto.getEntityName().length() == 0) {
            errors.rejectValue("entityName", "error.entityName.required", null, "EntityName Required");
            log.error("EntityName Required");
        }
        if (customerTemplateDto.getEntityId() == null || customerTemplateDto.getEntityId().length() == 0) {
            errors.rejectValue("entityId", "error.entityId.required", null, "Entity Id Required");
            log.error("entityId Required");
        }
        if (customerTemplateDto.getRouteName() == null || customerTemplateDto.getRouteName().length() == 0) {
            errors.rejectValue("routeName", "error.routeName.required", null, "Route Name Required");
            log.error("Route Name Required");
        }
        if (customerTemplateDto.getCustomers() == null || customerTemplateDto.getCustomers().size() == 0) {
            errors.rejectValue("customers", "error.customers.required", null, "Customers are Required");
            log.error("Customers are Required");
        }
        log.info("inside CustomerValidator.validate() Ends");
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }
}
