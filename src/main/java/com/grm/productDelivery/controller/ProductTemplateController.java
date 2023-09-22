package com.grm.productDelivery.controller;

import com.grm.productDelivery.services.Interfaces.ProductTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.grm.productDelivery.dto.ProductTemplateDto;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/productTemplate")
public class ProductTemplateController extends CommonController {

    @Autowired
    private ProductTemplateService productTemplateService;

    @PostMapping("/create")
    public ResponseEntity<String> createPrdTemplate(@Valid @RequestBody ProductTemplateDto requestData) {
        log.info("CreateProductTemplate Request ::" + requestData);
        try {
            boolean response = productTemplateService.create(requestData);
            if (response)
                return new ResponseEntity<>(successRequest("Product template created successfully.").toString(), HttpStatus.CREATED);
            return new ResponseEntity<>(badRequest("Error while creating product template in db").toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
