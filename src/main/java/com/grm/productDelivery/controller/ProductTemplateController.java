package com.grm.productDelivery.controller;

import com.grm.productDelivery.models.ProductTemplate;
import com.grm.productDelivery.services.Interfaces.ProductTemplateService;
import com.grm.productDelivery.util.EntityMessages;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.grm.productDelivery.dto.ProductTemplateDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/productTemplate")
public class ProductTemplateController extends CommonController {

    @Autowired
    private ProductTemplateService productTemplateService;

    @PostMapping("/create")
    @Operation(summary = "Create a Product Template")
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

    @PutMapping("/update")
    @Operation(summary = "Update a Product Template")
    public ResponseEntity<String> updatePrdTemplate(@Valid @RequestBody ProductTemplateDto requestData) {
        log.info("UpdateProductTemplate Request ::" + requestData);
        try {
            boolean response = productTemplateService.update(requestData);
            if (response)
                return new ResponseEntity<>(successRequest("Product template updated successfully.").toString(), HttpStatus.CREATED);
            return new ResponseEntity<>(badRequest("No record in the database to update").toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteById/{id}")
    @Operation(summary = "Delete a Product Template By Id")
    public ResponseEntity<?> deleteProdTempById(@PathVariable("id") String productId) {
        log.info("deleteEntityById :: Request id - " + productId);
        try {
            boolean status = productTemplateService.deleteProdTempById(productId);

            if (status)
                return new ResponseEntity<>(successRequest("Product template deleted successfully.").toString(), HttpStatus.CREATED);
            else
                return new ResponseEntity<>(badRequest("No record in the database to delete").toString(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Error occured while deleting record - " + e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findAll")
    @Operation(summary = "Get All Product Templates")
    public ResponseEntity<?> getProductTempByFindAll() {
        log.info("getProductTempByFindAll byFindAll :: request");
        try {
            List<ProductTemplate> data = productTemplateService.getProductTempByFindAll();
            if (data.size() >= 1)
                return new ResponseEntity<>(data, HttpStatus.OK);
            else
                return new ResponseEntity<>(successRequest(EntityMessages.noData).toString(), HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error occured while Getting productTemplateByfindALL  - " + e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/findById/{id}")
    @Operation(summary = "Get Product Template By Id")
    public ResponseEntity<?> getProdTemptById(@PathVariable("id") String productId) {
        log.info("getProdTemptById byFindById :: request");
        try {
            Optional<ProductTemplate> pt = productTemplateService.getProdTempById(productId);
            if (pt.isPresent())
                return new ResponseEntity<>(pt, HttpStatus.OK);
            else
                return new ResponseEntity<>(badRequest(EntityMessages.noDataFound + productId).toString(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Error occured while Getting productTemplateById  - " + e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
