package com.crymuzz.pedidocomposition_microservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseDetallePedidoDTO {
    private Long id;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal totalDetalle;
    private ResponseProductDTO producto;
}
