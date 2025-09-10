package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.ResponseDetallePedidoDTO;

import java.util.List;

public interface DetallePedidoService {
    List<ResponseDetallePedidoDTO> listarPorPedidoId(Long id);
}
