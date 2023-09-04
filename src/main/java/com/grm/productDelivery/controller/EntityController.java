package com.grm.productDelivery.controller;

import com.grm.productDelivery.format.RequestFormat;
import com.grm.productDelivery.models.Entity;
import com.grm.productDelivery.services.EntityService;
import com.grm.productDelivery.util.EntityMessages;
import com.grm.productDelivery.validator.EntityFormatValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/entity")
@Slf4j
public class EntityController extends CommonController {

    @Autowired
    public EntityService entityService;


    @PostMapping(value = "/save")
    @Operation(summary = "Create An Entity")
    public ResponseEntity<String> saveEntity(@RequestBody String event) {
        log.info("saveEntity :: request -" + event);
        RequestFormat requestFormat = new EntityFormatValidator(event);
        try {
            if (requestFormat.isValid()) {
                boolean response = entityService.saveEntity(event);
                if (response)
                    return new ResponseEntity<>(successRequest(requestFormat.getSuccessMessage()).toString(), HttpStatus.CREATED);
                return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "/findAll")
    @Operation(summary = "Get All Entities")
    public ResponseEntity<?> getEntityByFindAll() {
        log.info("getGRMProducts byFindAll :: request");
        try {
            List<Entity> data = entityService.getEntityByFindAll();
            if (data.size() >= 1)
                return new ResponseEntity<>(data, HttpStatus.OK);
            else
                return new ResponseEntity<>(successRequest(EntityMessages.noData).toString(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(badRequest(ex.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping(value = "/findById/{id}")
    @Operation(summary = "Get An Entity By Id")
    public ResponseEntity<?> getEntityById(@PathVariable("id") String productId) {
        log.info("getEntityById :: Request id - " + productId);
        try {
            Optional<Entity> data = entityService.getEntityById(productId);
            if (data.isPresent())
                return new ResponseEntity<>(data, HttpStatus.OK);
            else
                return new ResponseEntity<>(badRequest(EntityMessages.noDataFound + productId).toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteById/{id}")
    @Operation(summary = "Delete An Entity By Id")
    public ResponseEntity<?> deleteEntityById(@PathVariable("id") String productId) {
        log.info("deleteEntityById :: Request id - " + productId);
        try {
            boolean status = entityService.deleteEntityById(productId);

            if (status)
                return new ResponseEntity<>(successRequest(EntityMessages.deleteSts).toString(), HttpStatus.OK);
            else
                return new ResponseEntity<>(badRequest(EntityMessages.deleteErrSts + productId).toString(), HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Error occured while deleting record - " + e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    @Operation(summary = "Update an Entity")
    public ResponseEntity<String> updateEntity(@RequestBody String event) {
        log.info("updateEntity :: request -" + event);
        RequestFormat requestFormat = new EntityFormatValidator(event, "id");
        try {
            if (requestFormat.isValid()) {
                boolean response = entityService.updateEntity(event);
                if (response)
                    return new ResponseEntity<>(successRequest(requestFormat.getSuccessMessage()).toString(), HttpStatus.CREATED);
                return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping(value = "/findByName/{name}")
    @Operation(summary = "Get Entity By Name")
    public ResponseEntity<?> getEntityByName(@PathVariable("name") String prodName) {
        log.info("getEntityByName :: Request name - " + prodName);
        try {
            List<Entity> data = entityService.getEntityByName(prodName);
            if (data.size() >= 1)
                return new ResponseEntity<>(data, HttpStatus.OK);
            else
                return new ResponseEntity<>(badRequest(EntityMessages.noDataFound + prodName).toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
