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
public class CustomerModelDao {

    @Autowired
    private CustomerModelRepository customerModelRepository;

    /**
     * @param customerTemplate
     * @return CustomerModel Object
     * @throws Exception
     */
    public CustomerModel createCustomerModel(CustomerTemplate customerTemplate) throws Exception {
        log.info("inside CustomerDao.create() Begins");
        CustomerModel saveNewCustomerModel = new CustomerModel();
        saveNewCustomerModel.setCustomerModelId(UUID.randomUUID().toString().split("-")[0]);
        saveNewCustomerModel.setCreatedAt(customerTemplate.getCreatedAt());
        saveNewCustomerModel.setCreatedBy(customerTemplate.getCreatedBy());
        saveNewCustomerModel.setModifiedAt(customerTemplate.getModifiedAt());
        saveNewCustomerModel.setModifiedBy(customerTemplate.getModifiedBy());
        saveNewCustomerModel.setEntityName(customerTemplate.getEntityName());
        saveNewCustomerModel.setEntityId(customerTemplate.getEntityId());
        saveNewCustomerModel.setRouteName(customerTemplate.getRouteName());
        saveNewCustomerModel.setCustomers(customerTemplate.getCustomers());
        List<CustomerModel> existCustomerModel = getCustomerModelByRouteName(customerTemplate.getRouteName());
        if (existCustomerModel.size() >= 1) {
            throw new Exception("Customer Model is already exist");
        } else {
            customerModelRepository.insert(saveNewCustomerModel);
        }
        log.info("inside CustomerDao.create() Ends");
        return saveNewCustomerModel;
    }

    /**
     * @param routeName
     * @return List of CutomerModel
     */
    public List<CustomerModel> getCustomerModelByRouteName(String routeName) {
        return customerModelRepository.findByRouteName(routeName);
    }

    /**
     * @param customerModelId
     * @return CustomerModel Object
     * @throws ResourceNotFoundException
     */
    public CustomerModel getCustomerModelById(String customerModelId) throws ResourceNotFoundException {
        return customerModelRepository.findById(customerModelId).orElseThrow(() -> new ResourceNotFoundException("Customer Model not found for this customerModel id :: " + customerModelId));
    }

    /**
     * @param customerModel
     * @return CustomerModel Object
     */
    public CustomerModel updateCustomerModel(CustomerModel customerModel) {
        return customerModelRepository.save(customerModel);
    }

    /**
     * @param customerModelId
     * @throws ResourceNotFoundException
     */
    public void deleteCustomerModel(String customerModelId) throws ResourceNotFoundException {
        CustomerModel existingCustomerModel = customerModelRepository.findById(customerModelId).orElseThrow(() -> new ResourceNotFoundException("Customer Model not found for this customerModel id :: " + customerModelId));
        customerModelRepository.delete(existingCustomerModel);
    }

    /**
     * @return List of Customer Model Object
     */
    public List<CustomerModel> findAllCustomerModel() {
        return customerModelRepository.findAll();
    }

}
