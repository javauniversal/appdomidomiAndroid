package com.zonaapp.domidomi.implementation.slpash.controller;

import com.zonaapp.domidomi.model.Establishment;
import com.zonaapp.domidomi.model.Error;
import java.util.List;

/**
 * Created by emejia on 6/23/17.
 */

public interface ISplashView {

    void onEstablishmentSuccess(List<Establishment> establishments);
    void onEstablishmentFailure();

}
