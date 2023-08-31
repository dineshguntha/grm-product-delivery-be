package com.grm.productDelivery.service;

import com.grm.productDelivery.format.RequestFormat;
import com.grm.productDelivery.model.GRMProductDelivery;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.grm.productDelivery.repo.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GRMProductService extends RequestFormat {

    @Autowired
    public GRMProductRepository  grmProductRepository;

    public boolean saveGRMProduct(String source){
        JSONObject requestObject = new JSONObject(source);
        String name =(String) requestObject.get("name");
        String gst= requestObject.isNull("gst")?"":requestObject.get("gst").toString().trim();
        String accuntNum = requestObject.isNull("accountNumber")?"":requestObject.get("accountNumber").toString().trim();
        GRMProductDelivery gpd=new GRMProductDelivery(name,gst,accuntNum);
        List<GRMProductDelivery> record= grmProductRepository.findByName(name);
        if(record.size()>=1){
            failureReason = "GRM product "+ name +" already created in the database.";
            return false;
        }else{
            grmProductRepository.insert(gpd);
            successMessage = "GRM product " + name + " record created";
            return true;
        }

    }

}
