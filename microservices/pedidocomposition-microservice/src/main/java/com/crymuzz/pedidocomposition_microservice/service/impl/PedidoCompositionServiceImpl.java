package com.crymuzz.pedidocomposition_microservice.service.impl;

import com.crymuzz.pedidocomposition_microservice.client.PedidoClient;
import com.crymuzz.pedidocomposition_microservice.client.ProductoClient;
import com.crymuzz.pedidocomposition_microservice.dto.ApiResponse;
import com.crymuzz.pedidocomposition_microservice.dto.CreatePedidoDTO;
import com.crymuzz.pedidocomposition_microservice.dto.ResponsePedidoDTO;
import com.crymuzz.pedidocomposition_microservice.dto.ResponseProductDTO;
import com.crymuzz.pedidocomposition_microservice.service.PedidoCompositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoCompositionServiceImpl implements PedidoCompositionService {

    private final PedidoClient pedidoClient;
    private final ProductoClient productoClient;

    @Override
    public ApiResponse<ResponsePedidoDTO> crearPedido(CreatePedidoDTO dto) {
        dto.getDetalles().forEach(detalle -> {
            ApiResponse<ResponseProductDTO> response = productoClient.obtenerProductoPorId(detalle.getProductoId());
            if (response.getData() == null) {
                throw new IllegalArgumentException("Producto con ID " + detalle.getProductoId() + " no existe");
            }
        });

        return pedidoClient.crearPedido(dto);
    }
}