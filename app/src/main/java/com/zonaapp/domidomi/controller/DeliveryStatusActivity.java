package com.zonaapp.domidomi.controller;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.timqi.sectorprogressview.SectorProgressView;
import com.zonaapp.domidomi.R;

public class DeliveryStatusActivity extends AppCompatActivity {

    SectorProgressView colorfulRingProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_status);

        colorfulRingProgressView = (SectorProgressView) findViewById(R.id.spv);

        CountDownTimer mCountDownTimer;

        colorfulRingProgressView.setPercent(0);
        mCountDownTimer=new CountDownTimer(1140000,1) {

            @Override
            public void onTick(long millisUntilFinished) {
                colorfulRingProgressView.setPercent(((1140000 - millisUntilFinished) * 100) / 1140000);

            }

            @Override
            public void onFinish() {
                colorfulRingProgressView.setPercent(100);
            }
        };
        mCountDownTimer.start();
    }
}
