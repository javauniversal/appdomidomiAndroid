package com.zonaapp.domidomi.implementation.dashboard.controller;

import com.zonaapp.domidomi.model.Establishment;

import java.util.List;

/**
 * Created by emejia on 6/23/17.
 */

public interface IOrderView {

    void onSendOrderSuccess();
    void onSendOderFailure(String message);

}
