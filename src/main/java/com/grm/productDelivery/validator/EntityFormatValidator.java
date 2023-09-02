package com.grm.productDelivery.validator;

import com.grm.productDelivery.format.RequestFormat;
import org.json.JSONObject;

public class EntityFormatValidator extends RequestFormat {

    public EntityFormatValidator(String source) {
        try {
            requestObject = new JSONObject(source);
            if (requestObject.has(Entity.name.name()) && requestObject.has(Entity.gst.name()) && requestObject.has(Entity.accountNumber.name())) {
                String name = requestObject.isNull(Entity.name.name())?"":String.valueOf(requestObject.get(Entity.name.name())).trim();
                if (!name.isEmpty())
                    valid = true;
                else
                    failureReason = "GRM product name is required.";

            } else {
                failureReason = "Invalid Format for required field [" + Entity.name + "," + Entity.gst + "," + Entity.accountNumber + "]";
            }
        } catch (Exception ex) {
            failureReason = ex.getMessage();
        }

    }

    public EntityFormatValidator(String source,String id) {
        try {
            requestObject = new JSONObject(source);
            if (requestObject.has(Entity.id.name()) && requestObject.has(Entity.name.name()) && requestObject.has(Entity.gst.name()) && requestObject.has(Entity.accountNumber.name())) {
                String eid = requestObject.isNull(Entity.id.name())?"":String.valueOf(requestObject.get(Entity.id.name())).trim();
                String name = requestObject.isNull(Entity.name.name())?"":String.valueOf(requestObject.get(Entity.name.name())).trim();
                if (!eid.isEmpty() && !name.isEmpty())
                    valid = true;
                else
                    failureReason = "GRM product name is required.";

            } else {
                failureReason = "Invalid Entity Format for required field [" + Entity.id + "," + Entity.name + "," + Entity.gst + "," + Entity.accountNumber + "]";
            }
        } catch (Exception ex) {
            failureReason = ex.getMessage();
        }

    }
}
