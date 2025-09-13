package com.crymuzz.stockms_microservice.model.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    private String timestamp;
}