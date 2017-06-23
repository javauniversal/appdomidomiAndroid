package com.zonaapp.domidomi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by emejia on 6/23/17.
 */

public class Establishment {


    private String id;

    @SerializedName("descripcion")
    private String description;

    @SerializedName("direccion")
    private String address;

    @SerializedName("tiempoenvio")
    private String deliveryTime;

    @SerializedName("costoenvio")
    private String deliveryPrice;

    @SerializedName("latitud")
    private String latitude;

    @SerializedName("longitud")
    private String longitude;

    @SerializedName("distancia")
    private String distance;

    @SerializedName("estado")
    private String status;

    @SerializedName("horarioApertura")
    private String schedule;

    @SerializedName("productos")
    private List<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
