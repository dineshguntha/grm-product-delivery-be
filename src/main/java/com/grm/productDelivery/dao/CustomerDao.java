package com.grm.productDelivery.dao;

import com.grm.productDelivery.repositories.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

/**
 * @author timbernerslee
 */
@Slf4j
@Component
public class CustomerDao {

    @Autowired
    public CustomerRepository customerRepository;
}
