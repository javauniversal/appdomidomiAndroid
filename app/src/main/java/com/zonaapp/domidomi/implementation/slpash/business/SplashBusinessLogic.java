package com.zonaapp.domidomi.implementation.slpash.business;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zonaapp.domidomi.implementation.slpash.controller.ISplashView;
import com.zonaapp.domidomi.implementation.slpash.repository.IProductResponseHandler;
import com.zonaapp.domidomi.implementation.slpash.repository.SplashRepository;
import com.zonaapp.domidomi.model.Error;
import com.zonaapp.domidomi.model.Establishment;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emejia on 6/23/17.
 */

public class SplashBusinessLogic implements IProductResponseHandler {

    ISplashView mSplashView;

    public SplashBusinessLogic(ISplashView splashView){

        this.mSplashView = splashView;

    }

    public void getProduct(double latitude, double longitude){

        SplashRepository splashRepository = new SplashRepository();

        splashRepository.getProduct(latitude, longitude, this);
    }

    @Override
    public void onProductResponseHandler(Error error, JSONObject object) {

        if (error != null){
            mSplashView.onEstablishmentFailure();
        }else{
            Type listType = new TypeToken<ArrayList<Establishment>>() {
            }.getType();
            List<Establishment> establishments = null;
            try {establishments = new Gson().fromJson(String.valueOf(object.getJSONArray("mensaje")), listType);
                Log.d("","");
                if (establishments != null && establishments.size() > 0 && establishments.get(0).getProducts() != null && establishments.get(0).getProducts().size() > 0){
                    mSplashView.onEstablishmentSuccess(establishments);
                }else{
                    mSplashView.onEstablishmentFailure();
                }


                //mSplashView.onAPIResponseDepartment(null, departmentList);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
