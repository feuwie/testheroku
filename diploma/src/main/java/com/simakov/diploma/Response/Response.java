package com.simakov.diploma.Response;

import lombok.Data;

@Data
public class Response {
    private String status;
    private String message;
    private Object object;
    private String AUTH_TOKEN;
}