package com.zonaapp.domidomi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by emejia on 27/06/17.
 */

public class OrderProduct {

    @SerializedName("cantidad")
    private String quantity;

    @SerializedName("ordenes_id")
    private String orderId;

    @SerializedName("producto_id")
    private String productId;

    @SerializedName("precio")
    private String price;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
