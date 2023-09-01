package com.grm.productDelivery.controller;

import com.grm.productDelivery.format.RequestFormat;
import com.grm.productDelivery.model.GRMProductDelivery;
import com.grm.productDelivery.service.EntityService;
import com.grm.productDelivery.util.GRMData;
import com.grm.productDelivery.validator.EntityFormatValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entity")
public class EntityController extends CommonController{
private Logger logger= LoggerFactory.getLogger(EntityController.class);
    @Autowired
    public EntityService entityService;


    @PostMapping(value = "/save")
    public ResponseEntity<String> saveEntity(@RequestBody String event){
        logger.info("saveEntity :: request -"+event);
        RequestFormat  requestFormat = new EntityFormatValidator(event);
        try{
            if (requestFormat.isValid()){
                boolean response = entityService.saveEntity(event);
                if (response)
                    return new ResponseEntity<>(successRequest(requestFormat.getSuccessMessage()).toString(), HttpStatus.CREATED);
                return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping(value = "/findAll")
    public ResponseEntity<?> getEntityByFindAll(){
        logger.info("getGRMProducts byFindAll :: request");
        try{
            List<GRMProductDelivery> data= entityService.getEntityByFindAll();
            if(data.size()>=1)
                return new ResponseEntity<>(data, HttpStatus.OK);
            else
                return new ResponseEntity<>(successRequest(GRMData.noData).toString(), HttpStatus.OK);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return new ResponseEntity<>(badRequest(ex.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> getEntityById(@PathVariable("id") String productId) {
        logger.info("getEntityById :: Request id - "+productId);
        try {
            Optional<GRMProductDelivery> data = entityService.getEntityById(productId);
            if (data.isPresent())
                return new ResponseEntity<>(data, HttpStatus.OK);
            else
                return new ResponseEntity<>(badRequest(GRMData.noDataFound + productId).toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<?> deleteEntityById(@PathVariable("id") String productId) {
        logger.info("deleteEntityById :: Request id - "+productId);
        try{
           boolean status = entityService.deleteEntityById(productId);

           if(status)
               return new ResponseEntity<>(successRequest(GRMData.deleteSts).toString(), HttpStatus.OK);
           else
               return new ResponseEntity<>(badRequest(GRMData.deleteErrSts + productId).toString(), HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            logger.error("Error occured while deleting record - "+e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateEntity(@RequestBody String event){
        logger.info("updateEntity :: request -"+event);
        RequestFormat  requestFormat = new EntityFormatValidator(event, "id");
        try{
            if (requestFormat.isValid()){
                boolean response = entityService.updateEntity(event);
                if (response)
                    return new ResponseEntity<>(successRequest(requestFormat.getSuccessMessage()).toString(), HttpStatus.CREATED);
                return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
