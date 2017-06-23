package com.zonaapp.domidomi.implementation.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.zonaapp.domidomi.R;
import com.zonaapp.domidomi.implementation.createAccount.CreateAccountActivity;
import com.zonaapp.domidomi.model.Product;
import com.zonaapp.domidomi.util.PreferencesManager;

import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {

    TextView txtProductPrice;

    TextView txtProductName;

    TextView txtProductQnty;
    FloatingActionButton btnAddQty;
    FloatingActionButton btnRemoveQty;
    int productQty = 1;
    LinearLayout layoutOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);

        txtProductName = (TextView) findViewById(R.id.txtProductName) ;
        txtProductPrice = (TextView) findViewById(R.id.txtProductPrice) ;
        txtProductQnty = (TextView) findViewById(R.id.txtProductQty) ;
        btnAddQty = (FloatingActionButton) findViewById(R.id.btnAddQty) ;
        btnRemoveQty = (FloatingActionButton) findViewById(R.id.btnRemoveQty) ;
        layoutOrder = (LinearLayout) findViewById(R.id.layout_order) ;

        Product product = (Product) getIntent().getSerializableExtra("product");
        txtProductName.setText(product.getDescription());
        txtProductPrice.setText(product.getPrice());

        addHandlers();
        /*layoutOrder = (LinearLayout) findViewById(R.id.layout_order);
        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(DashboardActivity.this);
                progressDialog.setMessage("Enviando pedido...");
                progressDialog.show();

                JSONObject obj = new JSONObject();
                JSONObject notificacion = new JSONObject();
                JSONObject data = new JSONObject();

                try {
                    obj.put("to", "fSJ04sIFgvI:APA91bE9AETiiUpg24FAgErkAMq8_M7utPO1wpY0CRSI3i-x4yzeS-TpDlb6yBwX6glpDYk2gp43_NPZC8rbP8gyRUencUe3272XBO41Jk4ehCIHHQCIEgzCcY5Mjwfw5YPq5f2RiajQ");
                    notificacion.put("title", "Has recibido un nuevo pedido!");
                    notificacion.put("body", "Pizza de peperoni, cantidad: 1");

                    data.put("titulo", "Prueba desde el cel");
                    data.put("descripcion", "Prueba desde el cel");
                    obj.put("notification", notificacion);
                    obj.put("data", data);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = Constants.FIREBASE_URL_SERVER;
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,  obj.toString(),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(DashboardActivity.this, DeliveryStatusActivity.class);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                               Log.d("","");
                            }
                        }) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        headers.put("Authorization", "key=AAAAE-AQWIg:APA91bHKi7dH2zZj4WcgwSs2rt0gOAfICbZq0k0J4aHyEa0ggF44_JxO3BKvKLtWF1WjVF7ANADhdy4t7tfTLWZlbR0pyvZKrrEknQ3TTqRXpKkp6ESlF450rasis0-xqaVyiVxNcHkpLFiLyn_kcJ3zMf8HGeRLdg");

                        return headers;
                    }
                };

                request.setRetryPolicy(new DefaultRetryPolicy(
                        20000,
                        1,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                RequestQueue requestQueue = Volley.newRequestQueue(DashboardActivity.this);
                requestQueue.add(request);
            }
        });*/

    }

    private void addHandlers() {

        btnAddQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productQty != 10){
                    productQty ++;
                    txtProductQnty.setText(String.valueOf(productQty));
                }

            }
        });

        btnRemoveQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productQty != 1){
                    productQty --;
                    txtProductQnty.setText(String.valueOf(productQty));
                }
            }
        });

        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser();
            }


        });
    }

    private void validateUser() {
        if (PreferencesManager.getCurrentUser(this) != null){

        }else{
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
        }
    }

}
