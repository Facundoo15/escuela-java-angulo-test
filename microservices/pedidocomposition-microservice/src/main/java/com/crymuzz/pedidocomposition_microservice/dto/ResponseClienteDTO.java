package com.crymuzz.pedidocomposition_microservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseClienteDTO {
    private Long id;
    private String nombre;
    private String email;
    private LocalDate fechaRegistro;
    private boolean activo;
}
