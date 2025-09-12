package com.crymuzz.pedidocomposition_microservice.service;

import com.crymuzz.pedidocomposition_microservice.dto.ApiResponse;
import com.crymuzz.pedidocomposition_microservice.dto.CreatePedidoDTO;
import com.crymuzz.pedidocomposition_microservice.dto.ResponsePedidoDTO;

public interface PedidoCompositionService {
    ApiResponse<ResponsePedidoDTO> crearPedido(CreatePedidoDTO dto);
}
