package com.zonaapp.domidomi.implementation.dashboard.repository;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.zonaapp.domidomi.app.AppDomiDomi;
import com.zonaapp.domidomi.implementation.slpash.repository.IProductResponseHandler;
import com.zonaapp.domidomi.model.Error;
import com.zonaapp.domidomi.model.Order;
import com.zonaapp.domidomi.util.Constants;
import com.zonaapp.domidomi.util.VolleyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emejia on 6/23/17.
 */

public class OrderRepository {

    public static void sendOrder(final JSONObject order, final IOrderResponseHandler handler){

        String url = Constants.URL_API + "guardarordenes";


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("","");
                try {
                    JSONObject responeObject = new JSONObject(response);
                    boolean status = responeObject.getBoolean("estado");

                    if (status == true){
                        handler.onSendOrderResponse(null);
                    }else{
                        handler.onSendOrderResponse(new Error(String.valueOf("500"), null));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handler.onSendOrderResponse(new Error(String.valueOf("500"), "Ha ocurrido un error al intentar obtener los productos"));
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                //String parJSON = new Gson().toJson(order, Order.class);
                        params.put("data", order.toString());

                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                2000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleyManager.getInstance(AppDomiDomi.getInstance()).addToRequestQueue(request);
    }
}
