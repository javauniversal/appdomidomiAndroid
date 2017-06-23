package com.zonaapp.domidomi.implementation.slpash.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.zonaapp.domidomi.R;
import com.zonaapp.domidomi.implementation.dashboard.DashboardActivity;
import com.zonaapp.domidomi.implementation.slpash.business.SplashBusinessLogic;
import com.zonaapp.domidomi.model.Establishment;

import java.util.List;


public class SplashActivity extends AppCompatActivity implements ISplashView {

    ImageView imageViewPizza;
    SplashBusinessLogic mSplashBL;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageViewPizza = (ImageView) findViewById(R.id.imageSplash);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        mSplashBL = new SplashBusinessLogic(this);
        getProduct();
    }

    private void getProduct() {
        findViewById(R.id.imageSplash).animate().alpha(1).scaleY(1.5f).scaleX(1.5f).setDuration(2000L).setInterpolator(new AccelerateDecelerateInterpolator());

        mSplashBL.getProduct(6.21884, -75.57018);

    }

    @Override
    public void onEstablishmentSuccess(List<Establishment> establishments) {
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        intent.putExtra("product" , establishments.get(0).getProducts().get(0));
        startActivity(intent);
        finish();
    }

    @Override
    public void onEstablishmentFailure() {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title("Lo sentimos")
                .autoDismiss(false)
                .customView(R.layout.popup_no_establishments, wrapInScrollView)
                .positiveText("Aceptar")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        materialDialog.dismiss();
                        System.exit(1);
                    }
                })
                .show();
    }
}
