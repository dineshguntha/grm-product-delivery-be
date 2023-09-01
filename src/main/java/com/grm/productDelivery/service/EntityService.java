package com.grm.productDelivery.service;

import com.grm.productDelivery.format.RequestFormat;
import com.grm.productDelivery.model.GRMProductDelivery;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.grm.productDelivery.repo.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EntityService extends RequestFormat {

    @Autowired
    public EntityRepository entityRepository;

    public boolean saveEntity(String source){
        JSONObject requestObject = new JSONObject(source);
        String name =(String) requestObject.get("name");
        String gst= requestObject.isNull("gst")?"":requestObject.get("gst").toString().trim();
        String accuntNum = requestObject.isNull("accountNumber")?"":requestObject.get("accountNumber").toString().trim();
        GRMProductDelivery gpd=new GRMProductDelivery(name,gst,accuntNum);
        List<GRMProductDelivery> record= entityRepository.findByName(name);
        if(record.size()>=1){
            failureReason = "Product "+ name +" already created in the database.";
            return false;
        }else{
            entityRepository.insert(gpd);
            successMessage = "GRM product " + name + " record created";
            return true;
        }

    }
    public List<GRMProductDelivery> getEntityByFindAll(){
        return entityRepository.findAll();
    }

    public Optional<GRMProductDelivery> getEntityById(String id){
        return entityRepository.findById(id);
    }

    public boolean deleteEntityById(String id){
        Optional<GRMProductDelivery> data= getEntityById(id);
        if(data.isPresent()){
            entityRepository.deleteById(id);
            return true;

        }
       return false;
    }

    public boolean updateEntity(String source){
        JSONObject requestObject = new JSONObject(source);
        String id   =  (String)  requestObject.get("id");
        String name =(String) requestObject.get("name");
        String gst= requestObject.isNull("gst")?"":requestObject.get("gst").toString().trim();
        String accuntNum = requestObject.isNull("accountNumber")?"":requestObject.get("accountNumber").toString().trim();
        GRMProductDelivery gpd=new GRMProductDelivery(id,name,gst,accuntNum);
        Optional<GRMProductDelivery> data= getEntityById(id);
        if(data.isPresent()){
            entityRepository.save(gpd);
            successMessage = "GRM product " + name + " record updated";
            return true;

        }else {
            failureReason = "No record exist with id "+id;
            return  false;
        }

    }

}
