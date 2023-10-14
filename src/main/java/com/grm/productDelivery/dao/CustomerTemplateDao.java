package com.grm.productDelivery.dao;

import com.grm.productDelivery.dto.*;
import com.grm.productDelivery.exceptions.*;
import com.grm.productDelivery.models.*;
import com.grm.productDelivery.repositories.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * @author timbernerslee
 */
@Slf4j
@Component
public class CustomerTemplateDao {

    @Autowired
    private CustomerTemplateRepository customerModelRepository;

    /**
     * @param customerTemplateDto
     * @return CustomerTemplate Object
     * @throws Exception
     */
    public CustomerTemplate createCustomerTemplate(CustomerTemplateDto customerTemplateDto) throws Exception {
        log.info("inside CustomerTemplateDao.create() Begins");
        CustomerTemplate saveNewCustomerTemplate = new CustomerTemplate();
        saveNewCustomerTemplate.setCustomerTemplateId(UUID.randomUUID().toString().split("-")[0]);
        saveNewCustomerTemplate.setCreatedAt(customerTemplateDto.getCreatedAt());
        saveNewCustomerTemplate.setCreatedBy(customerTemplateDto.getCreatedBy());
        saveNewCustomerTemplate.setModifiedAt(customerTemplateDto.getModifiedAt());
        saveNewCustomerTemplate.setModifiedBy(customerTemplateDto.getModifiedBy());
        saveNewCustomerTemplate.setEntityName(customerTemplateDto.getEntityName());
        saveNewCustomerTemplate.setEntityId(customerTemplateDto.getEntityId());
        saveNewCustomerTemplate.setRouteName(customerTemplateDto.getRouteName());
        //saveNewCustomerTemplate.setCustomers(customerTemplateDto.getCustomers());
        List<CustomerTemplate> existCustomerTemplate = getCustomerTemplateByRouteName(customerTemplateDto.getRouteName());
        if (existCustomerTemplate.size() >= 1) {
            throw new Exception("Customer Template is already exist");
        } else {
            customerModelRepository.insert(saveNewCustomerTemplate);
        }
        log.info("inside CustomerTemplateDao.create() Ends");
        return saveNewCustomerTemplate;
    }

    /**
     * @param routeName
     * @return List of CustomerTemplate
     */
    public List<CustomerTemplate> getCustomerTemplateByRouteName(String routeName) {
        return customerModelRepository.findByRouteName(routeName);
    }

    /**
     * @param customerTemplateId
     * @return CustomerTemplate Object
     * @throws ResourceNotFoundException
     */
    public CustomerTemplate getCustomerTemplateByCustomerTemplateId(String customerTemplateId) throws ResourceNotFoundException {
        return customerModelRepository.findById(customerTemplateId).orElseThrow(() -> new ResourceNotFoundException("Customer Template not found for this customerTemplateId :: " + customerTemplateId));
    }

    /**
     * @param customerTemplate
     * @return CustomerTemplate Object
     */
    public CustomerTemplate updateCustomerTemplate(CustomerTemplate customerTemplate) {
        return customerModelRepository.save(customerTemplate);
    }

    /**
     * @param customerTemplateId
     * @throws ResourceNotFoundException
     */
    public void deleteCustomerTemplate(String customerTemplateId) throws ResourceNotFoundException {
        CustomerTemplate existingCustomerTemplate = customerModelRepository.findById(customerTemplateId).orElseThrow(() -> new ResourceNotFoundException("Customer Template not found for this customerTemplateId id :: " + customerTemplateId));
        customerModelRepository.delete(existingCustomerTemplate);
    }

    /**
     * @return List of CustomerTemplate Object
     */
    public List<CustomerTemplate> findAllCustomerTemplate() {
        return customerModelRepository.findAll();
    }

}
