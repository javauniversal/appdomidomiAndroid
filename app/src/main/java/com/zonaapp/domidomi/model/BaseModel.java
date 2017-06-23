package com.zonaapp.domidomi.model;

import com.google.gson.Gson;

/**
 * Created by emejia on 6/23/17.
 */

public class BaseModel {

    public String toJsonString() {
        return new Gson().toJson(this);
    }

    public static BaseModel objectFromJson(String json, Class<? extends BaseModel> type) throws com.google.gson.JsonParseException {
        return new Gson().fromJson(json, type);
    }
}
