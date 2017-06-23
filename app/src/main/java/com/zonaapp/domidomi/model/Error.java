package com.zonaapp.domidomi.model;

/**
 * Created by mauriciocarogutierrez on 11/10/16.
 *
 * Modelo que permite manejar el codigo y el mensaje en toda la aplicación
 */

public class Error {

    private String code;
    private String message;

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
