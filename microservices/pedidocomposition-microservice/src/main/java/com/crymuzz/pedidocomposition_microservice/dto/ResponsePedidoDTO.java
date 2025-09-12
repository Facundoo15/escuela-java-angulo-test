package com.crymuzz.pedidocomposition_microservice.dto;

import com.crymuzz.pedidocomposition_microservice.dto.enums.EstadoPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ResponsePedidoDTO {

    private Long id;
    private LocalDate fechaPedido;
    private EstadoPedido estadoPedido;
    private BigDecimal total;
    private ResponseClienteDTO cliente;
    private List<ResponseDetallePedidoDTO> detalles;
}
