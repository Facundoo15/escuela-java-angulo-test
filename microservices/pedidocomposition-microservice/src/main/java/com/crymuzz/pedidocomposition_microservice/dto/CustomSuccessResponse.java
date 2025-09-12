package com.crymuzz.pedidocomposition_microservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomSuccessResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;

    public CustomSuccessResponse(int status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.data = data;
    }
}