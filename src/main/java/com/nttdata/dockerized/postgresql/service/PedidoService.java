package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.CreatePedidoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponsePedidoDTO;

import java.util.List;

public interface PedidoService {
    ResponsePedidoDTO crearPedido(CreatePedidoDTO createPedidoDTO);
    List<ResponsePedidoDTO> listarPedidos();
    ResponsePedidoDTO obtenerPedidoId(Long id);
}
