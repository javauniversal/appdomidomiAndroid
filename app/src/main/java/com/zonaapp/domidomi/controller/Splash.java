package com.zonaapp.domidomi.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import com.zonaapp.domidomi.controller.DashboardActivity;

import com.zonaapp.domidomi.R;


public class Splash extends AppCompatActivity {

    ImageView imageViewPizza;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageViewPizza = (ImageView) findViewById(R.id.imageSplash);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        initAndFinish();

    }

        public void initAndFinish(){
            findViewById(R.id.imageSplash).animate().alpha(1).scaleY(1.5f).scaleX(1.5f).setDuration(2000L).setInterpolator(new AccelerateDecelerateInterpolator());
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Splash.this, DashboardActivity.class));
                    finish();
                }
            },5210L);

        }

}
