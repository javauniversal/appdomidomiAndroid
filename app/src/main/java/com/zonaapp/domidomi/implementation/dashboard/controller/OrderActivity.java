package com.zonaapp.domidomi.implementation.dashboard.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.zonaapp.domidomi.R;
import com.zonaapp.domidomi.implementation.createAccount.CreateAccountActivity;
import com.zonaapp.domidomi.implementation.dashboard.business.OrderBusinessLogic;
import com.zonaapp.domidomi.implementation.delivery.OrderStatusActivity;
import com.zonaapp.domidomi.model.Establishment;
import com.zonaapp.domidomi.model.Product;
import com.zonaapp.domidomi.util.PreferencesManager;

import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class OrderActivity extends AppCompatActivity implements IOrderView{

    TextView txtProductPrice;

    TextView txtProductName;
    ProgressDialog progressDialog;
    final int REQUEST_CODE_CREATE_ACCOUNT = 1;
    TextView txtProductQnty;
    FloatingActionButton btnAddQty;
    FloatingActionButton btnRemoveQty;
    int productQty = 1;
    LinearLayout layoutOrder;
    OrderBusinessLogic orderBusinessLogic;
    Product product;
    String idEstablishment;

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

         product = (Product) getIntent().getSerializableExtra("product");
        idEstablishment = getIntent().getStringExtra("idEstablishment");
        txtProductName.setText(product.getDescription());
        txtProductPrice.setText(product.getPrice());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Enviando pedido...");
        orderBusinessLogic = new OrderBusinessLogic(this);
        addHandlers();


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
        if (PreferencesManager.getCurrentUser() != null){
            sendOrderToServer();
        }else{
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivityForResult(intent, REQUEST_CODE_CREATE_ACCOUNT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CREATE_ACCOUNT && resultCode == RESULT_OK){
            sendOrderToServer();
        }
    }

    private void sendOrderToServer() {
        progressDialog.show();
        orderBusinessLogic.sendOrder(product, Integer.parseInt(txtProductQnty.getText().toString()), 6.21884 , -75.57018, idEstablishment);

    }

    @Override
    public void onSendOrderSuccess() {
        progressDialog.dismiss();
        Intent intent = new Intent(this, OrderStatusActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSendOderFailure() {
        Toasty.error(this, "Ha ocurrido un error al intentar enviar el pedido", Toast.LENGTH_SHORT, true).show();
        progressDialog.dismiss();
    }
}
