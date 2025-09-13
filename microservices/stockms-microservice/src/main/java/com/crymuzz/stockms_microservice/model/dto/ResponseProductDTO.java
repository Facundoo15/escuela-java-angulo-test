package com.crymuzz.stockms_microservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private ResponseCategoriaDTO categoria;
}
