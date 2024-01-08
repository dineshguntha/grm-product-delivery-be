package com.grm.productDelivery.controller;

import com.grm.productDelivery.services.Interfaces.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author timbernerslee
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class CustomerController {

    //@Autowired
    private CustomerService customerService;
}
