package com.crymuzz.stockms_microservice.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCategoriaDTO {
    private Long id;
    private String nombre;
}
