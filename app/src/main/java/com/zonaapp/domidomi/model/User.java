package com.zonaapp.domidomi.model;

/**
 * Created by emejia on 6/23/17.
 */

public class User extends BaseModel{

    private String names;

    private String lastName;

    private String address;

    private String phone;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
