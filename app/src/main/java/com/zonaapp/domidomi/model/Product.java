package com.zonaapp.domidomi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by emejia on 6/23/17.
 */

public class Product implements Serializable{

    private String id;

    @SerializedName("tipoproducto")
    private String type;

    @SerializedName("descripcion")
    private String description;

    @SerializedName("ingredientes")
    private String ingredients;

    @SerializedName("precio")
    private String price;

    @SerializedName("foto")
    private String photo;

    @SerializedName("estado")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
