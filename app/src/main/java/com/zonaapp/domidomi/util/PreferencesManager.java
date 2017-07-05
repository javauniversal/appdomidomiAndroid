package com.zonaapp.domidomi.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Base64;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zonaapp.domidomi.app.AppDomiDomi;
import com.zonaapp.domidomi.model.BaseModel;
import com.zonaapp.domidomi.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Otto on 06/04/2017.
 */

public class PreferencesManager {

    private static SharedPreferences getSharedPreferences() {
        return AppDomiDomi.getInstance().getApplicationContext().getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE);
    }

    public static void saveUser(User user) {
        SharedPreferences.Editor editor = PreferencesManager.getSharedPreferences().edit();
        if(user == null) {
            editor.remove(Constants.CURRENT_USER);
        }else {
            editor.putString(Constants.CURRENT_USER, user.toJsonString());
        }
        editor.apply();
    }

    public static String getImeiDevice() {
        return Settings.Secure.getString(AppDomiDomi.getInstance().getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static void saveBoolean(String key, boolean value){
        SharedPreferences.Editor editor = PreferencesManager.getSharedPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key){
        return PreferencesManager.getSharedPreferences().getBoolean(key, false);
    }

    public static User getCurrentUser() {
        String userJson = PreferencesManager.getSharedPreferences().getString(Constants.CURRENT_USER,
                Constants.USER_NOT_FOUND);

        if(userJson.equals(Constants.USER_NOT_FOUND))
            return null;

        return (User) BaseModel.objectFromJson(userJson, User.class);
    }

}
