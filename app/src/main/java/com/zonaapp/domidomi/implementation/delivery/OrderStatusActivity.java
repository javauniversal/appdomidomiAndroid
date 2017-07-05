package com.zonaapp.domidomi.implementation.delivery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.timqi.sectorprogressview.SectorProgressView;
import com.zonaapp.domidomi.R;
import com.zonaapp.domidomi.util.Constants;
import com.zonaapp.domidomi.util.OrderTimerService;
import com.zonaapp.domidomi.util.PreferencesManager;

import java.util.concurrent.TimeUnit;

public class OrderStatusActivity extends AppCompatActivity {

    SectorProgressView colorfulRingProgressView;
    ImageView imagePizza;
    private static final String FORMAT = "%02d:%02d";
    TextView txtOrderTime;

    CountDownTimer cdt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_status);

        colorfulRingProgressView = (SectorProgressView) findViewById(R.id.spv);
        imagePizza = (ImageView) findViewById(R.id.image_pizza);
        txtOrderTime = (TextView) findViewById(R.id.txtOrderTime);
        cdt = new CountDownTimer(1140000, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTick(long millisUntilFinished) {

                float percent = ((1140000 - millisUntilFinished) * 100) / 1140000;

                if (percent > 0 && percent <= 16.8) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza1));
                } else if (percent > 16.8 && percent <= 33.6) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza2));
                } else if (percent > 33.6 && percent <= 50.4) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza3));
                } else if (percent > 50.4 && percent <= 67.2) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza4));
                } else if (percent > 67.2 && percent <= 84) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza5));
                } else if (percent > 84) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza6));
                }
                colorfulRingProgressView.setPercent(((1140000 - millisUntilFinished) * 100) / 1140000);
                txtOrderTime.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))) + "MIN");
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onFinish() {
                imagePizza.setBackground(getResources().getDrawable(R.drawable.fin));
                txtOrderTime.setText("TU PEDIDO ESTÁ LISTO");
            }
        };

        cdt.start();
       /* boolean isOrderActive = getIntent().getBooleanExtra("isOrderActive", false);
        if (isOrderActive){
            registerReceiver(br, new IntentFilter(OrderTimerService.COUNTDOWN_BR));
        }else {
            startService(new Intent(this, OrderTimerService.class));
        }*/
    }
    /*private BroadcastReceiver br = new BroadcastReceiver() {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onReceive(Context context, Intent intent) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            boolean finished = intent.getBooleanExtra("finished", false);

            if (finished){
                PreferencesManager.saveBoolean(Constants.CURRENT_ORDER, false);
                stopService(new Intent(OrderStatusActivity.this, OrderTimerService.class));
            }else {
                float percent = ((1140000 - millisUntilFinished) * 100) / 1140000;

                if (percent > 0 && percent <= 16.8) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza1));
                } else if (percent > 16.8 && percent <= 33.6) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza2));
                } else if (percent > 33.6 && percent <= 50.4) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza3));
                } else if (percent > 50.4 && percent <= 67.2) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza4));
                } else if (percent > 67.2 && percent <= 84) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza5));
                } else if (percent > 84) {
                    imagePizza.setBackground(getResources().getDrawable(R.drawable.pizza6));
                }
                colorfulRingProgressView.setPercent(((1140000 - millisUntilFinished) * 100) / 1140000);
                txtOrderTime.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }
        }
    };*/
}
