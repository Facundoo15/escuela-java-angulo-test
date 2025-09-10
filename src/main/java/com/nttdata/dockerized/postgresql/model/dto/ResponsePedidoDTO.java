package com.nttdata.dockerized.postgresql.model.dto;

import com.nttdata.dockerized.postgresql.model.enums.EstadoPedido;
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
