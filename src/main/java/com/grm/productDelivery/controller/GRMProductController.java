package com.grm.productDelivery.controller;

import com.grm.productDelivery.format.RequestFormat;
import com.grm.productDelivery.service.GRMProductService;
import com.grm.productDelivery.validator.GRMProductFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GRMProductController extends CommonController{

    @Autowired
    public GRMProductService grmProductService;

    @PostMapping(value = "/createGRM")
    public ResponseEntity<String> createGRMProduct(@RequestBody String event){
        RequestFormat  requestFormat = new GRMProductFormat(event);
        try{
            if (requestFormat.isValid()){
                boolean response = grmProductService.saveGRMProduct(event);
                if (response)
                    return new ResponseEntity<>(successRequest(requestFormat.getSuccessMessage()).toString(), HttpStatus.OK);
                return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(badRequest(requestFormat.getFailureReason()).toString(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(badRequest(e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
