package com.zonaapp.domidomi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by emejia on 27/06/17.
 */

public class Order {

    @SerializedName("nombrecliente")
    private String clientName;

    @SerializedName("direccion")
    private String clientAddress;

    @SerializedName("celular")
    private String clientPhone;

    @SerializedName("latitud")
    private String clientLatitude;

    @SerializedName("longitud")
    private String clientLongitude;

    @SerializedName("idestablecimiento")
    private String idEstablishment;

    @SerializedName("emei")
    private String phoneImei;

    @SerializedName("productos")
    private List<OrderProduct> products;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientLatitude() {
        return clientLatitude;
    }

    public void setClientLatitude(String clientLatitude) {
        this.clientLatitude = clientLatitude;
    }

    public String getClientLongitude() {
        return clientLongitude;
    }

    public void setClientLongitude(String clientLongitude) {
        this.clientLongitude = clientLongitude;
    }

    public String getIdEstablishment() {
        return idEstablishment;
    }

    public void setIdEstablishment(String idEstablishment) {
        this.idEstablishment = idEstablishment;
    }

    public String getPhoneImei() {
        return phoneImei;
    }

    public void setPhoneImei(String phoneImei) {
        this.phoneImei = phoneImei;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }
}
