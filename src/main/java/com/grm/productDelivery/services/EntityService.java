package com.grm.productDelivery.services;

import com.grm.productDelivery.format.RequestFormat;
import com.grm.productDelivery.repositories.EntityRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EntityService extends RequestFormat {

    @Autowired
    public EntityRepository entityRepository;

    public boolean saveEntity(String source) {
        JSONObject requestObject = new JSONObject(source);
        String name = (String) requestObject.get("name");
        String gst = requestObject.isNull("gst") ? "" : requestObject.get("gst").toString().trim();
        String accuntNum = requestObject.isNull("accountNumber") ? "" : requestObject.get("accountNumber").toString().trim();
        com.grm.productDelivery.models.Entity gpd = new com.grm.productDelivery.models.Entity(name, gst, accuntNum);
        List<com.grm.productDelivery.models.Entity> record = entityRepository.findByName(name);
        if (record.size() >= 1) {
            failureReason = "Product " + name + " already created in the database.";
            return false;
        } else {
            entityRepository.insert(gpd);
            successMessage = "GRM product " + name + " record created";
            return true;
        }

    }

    public List<com.grm.productDelivery.models.Entity> getEntityByFindAll() {
        return entityRepository.findAll();
    }

    public Optional<com.grm.productDelivery.models.Entity> getEntityById(String id) {
        return entityRepository.findById(id);
    }

    public boolean deleteEntityById(String id) {
        Optional<com.grm.productDelivery.models.Entity> data = getEntityById(id);
        if (data.isPresent()) {
            entityRepository.deleteById(id);
            return true;

        }
        return false;
    }

    public boolean updateEntity(String source) {
        JSONObject requestObject = new JSONObject(source);
        String id = (String) requestObject.get("id");
        String name = (String) requestObject.get("name");
        String gst = requestObject.isNull("gst") ? "" : requestObject.get("gst").toString().trim();
        String accuntNum = requestObject.isNull("accountNumber") ? "" : requestObject.get("accountNumber").toString().trim();
       com.grm.productDelivery.models.Entity gpd = new com.grm.productDelivery.models.Entity(id, name, gst, accuntNum);
        Optional<com.grm.productDelivery.models.Entity> data = getEntityById(id);
        if (data.isPresent()) {
            entityRepository.save(gpd);
            successMessage = "GRM product " + name + " record updated";
            return true;

        } else {
            failureReason = "No record exist with id " + id;
            return false;
        }

    }

    public List<com.grm.productDelivery.models.Entity> getEntityByName(String name) {
        return entityRepository.findByName(name);
    }

}
