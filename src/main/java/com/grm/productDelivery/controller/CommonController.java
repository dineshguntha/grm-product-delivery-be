package com.grm.productDelivery.controller;

import org.json.JSONObject;

public abstract class CommonController {

    protected JSONObject badRequest(String reason) {
        JSONObject badRequest = new JSONObject();
        badRequest.put("status", "false");
        badRequest.put("error", reason);
        return badRequest;
    }

    protected JSONObject successRequest(String message) {
        JSONObject sucResponse = new JSONObject();
        sucResponse.put("status", true);
        sucResponse.put("Message", message);
        return sucResponse;
    }
}
