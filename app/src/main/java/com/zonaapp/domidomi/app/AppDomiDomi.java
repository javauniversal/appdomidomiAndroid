package com.zonaapp.domidomi.app;

import android.app.Application;
import android.content.IntentFilter;

/**
 * Created by mauriciocarogutierrez on 11/10/16.
 */

public class AppDomiDomi extends Application {


    static AppDomiDomi instance;


    public static AppDomiDomi getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();

    }

}
