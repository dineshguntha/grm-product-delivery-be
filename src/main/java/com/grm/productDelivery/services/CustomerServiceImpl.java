package com.grm.productDelivery.services;

import com.grm.productDelivery.dao.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author timbernerslee
 */
@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerTemplateDao customerDao;
}
