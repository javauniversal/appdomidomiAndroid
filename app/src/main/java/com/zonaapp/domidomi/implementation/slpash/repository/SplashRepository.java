package com.zonaapp.domidomi.implementation.slpash.repository;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.zonaapp.domidomi.app.AppDomiDomi;
import com.zonaapp.domidomi.util.Constants;
import com.zonaapp.domidomi.model.Error;
import com.zonaapp.domidomi.util.VolleyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emejia on 6/23/17.
 */

public class SplashRepository {

    public static void getProduct(double latitude, double longitude, final IProductResponseHandler handler){

        String url = Constants.URL_API + "establecimiento" + "/" +latitude + "/" + longitude;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean statusCode = response.getBoolean("estado");
                    if (statusCode == true){
                        handler.onProductResponseHandler(null, response);
                    }else{
                        handler.onProductResponseHandler(new Error(String.valueOf("500"), response.getString("mensaje")), null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handler.onProductResponseHandler(new Error(String.valueOf("500"), "Ha ocurrido un error al intentar obtener los productos"), null);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                2000,
                1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleyManager.getInstance(AppDomiDomi.getInstance()).addToRequestQueue(request);
    }
}
