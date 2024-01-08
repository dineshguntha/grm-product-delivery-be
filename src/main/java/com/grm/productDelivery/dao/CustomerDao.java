package com.grm.productDelivery.dao;

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
public class CustomerDao {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * @param customerId
     * @return Customer Object
     */
    public Customer getCustomerByCustomerId(String customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this customerId :: " + customerId));
    }

    /**
     * @param name
     * @return Customer Object
     */
    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name).stream().filter(customer -> customer.getName().equalsIgnoreCase(name)).findAny().get();
    }

    /**
     * @param mobile
     * @return Customer Object
     */
    public Customer getCustomerByMobile(String mobile) {
        return customerRepository.findByMobile(mobile).stream().filter(customer -> customer.getMobile().equalsIgnoreCase(mobile)).findAny().get();
    }

    /**
     * @param email
     * @return Customer Object
     */
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).stream().filter(customer -> customer.getEmail().equalsIgnoreCase(email)).findAny().get();
    }

    /**
     * @param customer
     * @return Customer Object
     */
    public Customer updateUser(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * @param customerId
     * @throws ResourceNotFoundException
     */
    public void deleteUser(String customerId) throws ResourceNotFoundException {
        Customer existingCustomer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found for this customerId :: " + customerId));
        customerRepository.delete(existingCustomer);
    }

    /**
     * @return List of Customer
     */
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }


}
