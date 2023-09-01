package com.grm.productDelivery.controller;

import com.grm.productDelivery.format.RequestFormat;
import com.grm.productDelivery.model.GRMProductDelivery;
import com.grm.productDelivery.service.GRMProductService;
import com.grm.productDelivery.util.GRMData;
import com.grm.productDelivery.validator.GRMProductFormat;
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
    public GRMProductService grmProductService;


    @PostMapping(value = "/save")
    public ResponseEntity<String> createGRMProduct(@RequestBody String event){
        logger.info("createGRMProduct :: request -"+event);
        RequestFormat  requestFormat = new GRMProductFormat(event);
        try{
            if (requestFormat.isValid()){
                boolean response = grmProductService.saveGRMProduct(event);
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
    public ResponseEntity<?> getGRMProducts(){
        logger.info("getGRMProducts byFindAll :: request");
        try{
            List<GRMProductDelivery> data=grmProductService.getGRMProducts();
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
    public ResponseEntity<?> getGRMProductById(@PathVariable("id") String productId) {
        logger.info("getGRMProductById :: Request id - "+productId);
        try {
            Optional<GRMProductDelivery> data = grmProductService.getGRMProductById(productId);
            if (data.isPresent())
                return new ResponseEntity<>(data, HttpStatus.OK);
            else
                return new ResponseEntity<>(badRequest(GRMData.noDataFound + productId).toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
