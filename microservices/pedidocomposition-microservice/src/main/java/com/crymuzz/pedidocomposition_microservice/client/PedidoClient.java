package com.crymuzz.pedidocomposition_microservice.client;

import com.crymuzz.pedidocomposition_microservice.dto.ApiResponse;
import com.crymuzz.pedidocomposition_microservice.dto.CreatePedidoDTO;
import com.crymuzz.pedidocomposition_microservice.dto.ResponsePedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pedido-microservice")
public interface PedidoClient {
    @PostMapping("/api/pedidos")
    ApiResponse<ResponsePedidoDTO> crearPedido(@RequestBody CreatePedidoDTO dto);
}
