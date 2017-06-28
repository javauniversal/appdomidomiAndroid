package com.zonaapp.domidomi.implementation.slpash.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.zonaapp.domidomi.R;
import com.zonaapp.domidomi.implementation.dashboard.controller.OrderActivity;
import com.zonaapp.domidomi.implementation.slpash.business.SplashBusinessLogic;
import com.zonaapp.domidomi.model.Establishment;

import java.util.List;

import es.dmoral.toasty.Toasty;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static java.security.AccessController.getContext;


public class SplashActivity extends AppCompatActivity implements ISplashView, LocationListener {

    ImageView imageViewPizza;
    SplashBusinessLogic mSplashBL;
    private Location mUserLocation;
    private LocationManager mLocationManager;
    private final int LOCATION_SETTINGS = 1;

    private static final int RC_LOCATION_CONTACTS_PERM = 124;
    String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        imageViewPizza = (ImageView) findViewById(R.id.imageSplash);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        mSplashBL = new SplashBusinessLogic(this);
        findViewById(R.id.imageSplash).animate().alpha(1).scaleY(1.5f).scaleX(1.5f).setDuration(2000L).setInterpolator(new AccelerateDecelerateInterpolator());
        validateProviders();

    }

    private void getProduct() {

        mSplashBL.getProduct(mUserLocation.getLatitude(), mUserLocation.getLongitude());

    }

    @Override
    public void onEstablishmentSuccess(List<Establishment> establishments) {
        Intent intent = new Intent(SplashActivity.this, OrderActivity.class);
        intent.putExtra("product", establishments.get(0).getProducts().get(0));
        intent.putExtra("idEstablishment", establishments.get(0).getId());
        intent.putExtra("latitude", establishments.get(0).getLatitude());
        intent.putExtra("longitude", establishments.get(0).getLongitude());
        intent.putExtra("clientLatitude", mUserLocation.getLatitude());
        intent.putExtra("clientLongitude", mUserLocation.getLongitude());
        startActivity(intent);
        finish();
    }

    @Override
    public void onEstablishmentFailure() {
        boolean wrapInScrollView = true;
        new MaterialDialog.Builder(this)
                .title("Lo sentimos")
                .autoDismiss(false)
                .cancelable(false)
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

    @Override
    public void onLocationChanged(Location location) {
        mUserLocation = location;
        getProduct();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void validateProviders() {
        if (Build.VERSION.SDK_INT >= 23) {
            validatePermissions();
        } else {
            if (!isLocationEnabled()) {
                showAlertNoGps();
                return;
            } else {
                initializeLocationService();
            }
        }
    }

    private void showAlertNoGps() {
        new MaterialDialog.Builder(this)
                .title("Lo sentimos")
                .cancelable(false)
                .autoDismiss(false)
                .content("Para usar nuestro servicio, es necesario tener tu GPS activo.")
                .positiveText("Activar Gps")
                .negativeText("Cancelar")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, LOCATION_SETTINGS);
                        materialDialog.dismiss();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        materialDialog.dismiss();
                        System.exit(1);
                    }
                })
                .show();
    }

    public void initializeLocationService() {
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 10, this);
    }

    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    private void validatePermissions() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            if (!validateThatLocationEnabled(this)) {
                showAlertNoGps();
            } else {
                initializeLocationService();
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_service_location), RC_LOCATION_CONTACTS_PERM, perms);
        }
    }

    private boolean isLocationEnabled() {
        return mLocationManager != null && mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean validateThatLocationEnabled(FragmentActivity activity) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(activity.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 124: {
                validatePermissionsGranted();
            }

        }
    }

    private void validatePermissionsGranted() {
        if (EasyPermissions.hasPermissions(this, perms)) {
            if (!validateThatLocationEnabled(this)) {
                showAlertNoGps();
            } else {
                initializeLocationService();
            }
        } else {
            showAlertNoGps();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOCATION_SETTINGS) {
            validateProviders();
        }
    }
}
