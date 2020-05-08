package com.simakov.diploma.Response;

//import lombok.Data;

//@Data
public class Response {
    private String status;
    private String message;
    private Object object;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public void setAUTH_TOKEN(String AUTH_TOKEN) {
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    private String AUTH_TOKEN;
}