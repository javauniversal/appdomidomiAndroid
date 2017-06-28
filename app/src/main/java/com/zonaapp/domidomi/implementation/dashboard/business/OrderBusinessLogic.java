package com.zonaapp.domidomi.implementation.dashboard.business;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zonaapp.domidomi.implementation.dashboard.controller.IOrderView;
import com.zonaapp.domidomi.implementation.dashboard.repository.IOrderResponseHandler;
import com.zonaapp.domidomi.implementation.dashboard.repository.OrderRepository;
import com.zonaapp.domidomi.implementation.slpash.controller.ISplashView;
import com.zonaapp.domidomi.implementation.slpash.repository.IProductResponseHandler;
import com.zonaapp.domidomi.implementation.slpash.repository.SplashRepository;
import com.zonaapp.domidomi.model.Error;
import com.zonaapp.domidomi.model.Establishment;
import com.zonaapp.domidomi.model.Order;
import com.zonaapp.domidomi.model.OrderProduct;
import com.zonaapp.domidomi.model.Product;
import com.zonaapp.domidomi.model.User;
import com.zonaapp.domidomi.util.PreferencesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emejia on 6/23/17.
 */

public class OrderBusinessLogic implements IOrderResponseHandler {

    IOrderView mOrderView;

    public OrderBusinessLogic(IOrderView orderView){

        this.mOrderView = orderView;

    }

    public void sendOrder(Product product, int quantity, double latitude, double longitude, String idEstablishment){

        OrderRepository orderRepository = new OrderRepository();

        JSONObject objOrder = new JSONObject();
        JSONArray productsArray = new JSONArray();

        JSONObject orderProduct = new JSONObject();
        User user = PreferencesManager.getCurrentUser();

        try {
            orderProduct.put("cantidad", String.valueOf(quantity));
            orderProduct.put("precio", product.getPrice());
            orderProduct.put("producto_id", product.getId());
            productsArray.put(orderProduct);
            objOrder.put("productos", productsArray);
            objOrder.put("direccion", user.getAddress());
            objOrder.put("latitud", String.valueOf(latitude));
            objOrder.put("longitud", String.valueOf(longitude));
            objOrder.put("idestablecimiento", Integer.parseInt(idEstablishment));
            objOrder.put("nombrecliente", user.getNames() + " " + user.getLastName());
            objOrder.put("celular", user.getPhone());
            objOrder.put("emei", "23456resdfty");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        orderRepository.sendOrder(objOrder, this);
    }


    @Override
    public void onSendOrderResponse(Error error) {
        if (error == null){
            mOrderView.onSendOrderSuccess();
        }else{
            mOrderView.onSendOderFailure();
        }
    }
}
