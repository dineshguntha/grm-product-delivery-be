package com.grm.productDelivery.format;


import org.json.JSONObject;


public abstract class RequestFormat {
    protected boolean valid=false;
    protected JSONObject requestObject;
    public static String failureReason;
    public static String successMessage;

    public enum Entity {
       id, name, gst, accountNumber;
    }

    public boolean isValid() {
        return valid;
    }

    public JSONObject getRequestObject() {
        return requestObject;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public static String getSuccessMessage() {
        return successMessage;
    }
}
