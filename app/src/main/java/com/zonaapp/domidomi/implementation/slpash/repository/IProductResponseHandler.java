package com.zonaapp.domidomi.implementation.slpash.repository;

import org.json.JSONObject;
import com.zonaapp.domidomi.model.Error;
/**
 * Created by emejia on 6/23/17.
 */

public interface IProductResponseHandler {

    void onProductResponseHandler(Error error, JSONObject object);
}
