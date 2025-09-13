package com.crymuzz.stockms_microservice.service.impl;

import com.crymuzz.stockms_microservice.client.ProductoClient;
import com.crymuzz.stockms_microservice.exception.ResourceNotFoundException;
import com.crymuzz.stockms_microservice.mapper.StockMapper;
import com.crymuzz.stockms_microservice.model.dto.CreateStockDTO;
import com.crymuzz.stockms_microservice.model.dto.ResponseStockDTO;
import com.crymuzz.stockms_microservice.model.entity.Stock;
import com.crymuzz.stockms_microservice.repository.StockRepository;
import com.crymuzz.stockms_microservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;
    private final ProductoClient productoClient;

    @Override
    @Transactional
    public ResponseStockDTO crearStock(CreateStockDTO dto) {
        var response = productoClient.obtenerProductoPorId(dto.getProductId().longValue());

        if (response == null || response.getData() == null) {
            throw new ResourceNotFoundException("Producto con ID " + dto.getProductId() + " no existe");
        }

        Stock stock = stockMapper.toEntity(dto);
        stock = stockRepository.save(stock);

        return stockMapper.toResponse(stock);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseStockDTO obtenerPorId(Integer id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock con ID " + id + " no encontrado"));
        return stockMapper.toResponse(stock);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseStockDTO> obtenerPorProducto(Integer productId) {
        var response = productoClient.obtenerProductoPorId(productId.longValue());
        if (response == null || response.getData() == null) {
            throw new ResourceNotFoundException("Producto con ID " + productId + " no existe");
        }

        List<Stock> stocks = stockRepository.findByProductId(productId);
        return stockMapper.toResponseList(stocks);
    }

    @Override
    @Transactional
    public ResponseStockDTO actualizarCantidad(Integer stockId, Integer nuevaCantidad) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock con ID " + stockId + " no encontrado"));

        stock.setQuantity(nuevaCantidad);
        Stock actualizado = stockRepository.save(stock);

        return stockMapper.toResponse(actualizado);
    }

    @Override
    @Transactional
    public void eliminarStock(Integer stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock con ID " + stockId + " no encontrado"));
        stockRepository.delete(stock);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseStockDTO> listarTodos() {
        List<Stock> stocks = stockRepository.findAll();
        return stockMapper.toResponseList(stocks);
    }
}
