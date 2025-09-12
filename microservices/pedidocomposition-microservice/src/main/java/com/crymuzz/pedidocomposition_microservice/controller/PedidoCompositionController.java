package com.crymuzz.pedidocomposition_microservice.controller;

import com.crymuzz.pedidocomposition_microservice.dto.ApiResponse;
import com.crymuzz.pedidocomposition_microservice.dto.CreatePedidoDTO;
import com.crymuzz.pedidocomposition_microservice.dto.ResponsePedidoDTO;
import com.crymuzz.pedidocomposition_microservice.service.PedidoCompositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/composition/pedidos")
@RequiredArgsConstructor
public class PedidoCompositionController {

    private final PedidoCompositionService pedidoCompositionService;

    @PostMapping
    public ApiResponse<ResponsePedidoDTO> crear(@RequestBody @Valid CreatePedidoDTO dto) {
        return pedidoCompositionService.crearPedido(dto);
    }
}