package com.zonaapp.domidomi.implementation.dashboard.repository;

import com.zonaapp.domidomi.model.Error;

import org.json.JSONObject;

/**
 * Created by emejia on 6/23/17.
 */

public interface IOrderResponseHandler {

    void onSendOrderResponse(Error error);
}
