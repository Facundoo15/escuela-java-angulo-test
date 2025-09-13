package com.crymuzz.stockms_microservice.service;

import com.crymuzz.stockms_microservice.model.dto.CreateStockDTO;
import com.crymuzz.stockms_microservice.model.dto.ResponseStockDTO;

import java.util.List;

public interface StockService {
    ResponseStockDTO crearStock(CreateStockDTO dto);
    ResponseStockDTO obtenerPorId(Integer id);
    List<ResponseStockDTO> obtenerPorProducto(Integer productId);
    ResponseStockDTO actualizarCantidad(Integer stockId, Integer nuevaCantidad);
    void eliminarStock(Integer stockId);
    List<ResponseStockDTO> listarTodos();
}
