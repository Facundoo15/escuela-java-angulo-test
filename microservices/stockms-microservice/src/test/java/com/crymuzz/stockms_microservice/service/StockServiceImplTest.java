package com.crymuzz.stockms_microservice.service;

import com.crymuzz.stockms_microservice.client.ProductoClient;
import com.crymuzz.stockms_microservice.exception.ResourceNotFoundException;
import com.crymuzz.stockms_microservice.mapper.StockMapper;
import com.crymuzz.stockms_microservice.model.dto.*;
import com.crymuzz.stockms_microservice.model.entity.Stock;
import com.crymuzz.stockms_microservice.repository.StockRepository;
import com.crymuzz.stockms_microservice.service.impl.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;
    @Mock
    private StockMapper stockMapper;
    @Mock
    private ProductoClient productoClient;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void testCrearStock_Success() {
        CreateStockDTO dto = new CreateStockDTO(1, 10, 2);
        Stock stockEntity = new Stock(1, 1, 10, 2);
        ResponseStockDTO response = new ResponseStockDTO(1, 1, 10, 2);

        ResponseProductDTO producto = new ResponseProductDTO(
                1L,
                "Laptop Gamer",
                new BigDecimal("2500.00"),
                new ResponseCategoriaDTO(1L, "Electrónica")
        );

        ApiResponse<ResponseProductDTO> apiResponse = new ApiResponse<>();
        apiResponse.setData(producto);
        apiResponse.setStatus(200);
        apiResponse.setMessage("ok");
        apiResponse.setTimestamp("2025-09-12T19:24:11");

        when(productoClient.obtenerProductoPorId(1L)).thenReturn(apiResponse);
        when(stockMapper.toEntity(dto)).thenReturn(stockEntity);
        when(stockRepository.save(stockEntity)).thenReturn(stockEntity);
        when(stockMapper.toResponse(stockEntity)).thenReturn(response);

        ResponseStockDTO result = stockService.crearStock(dto);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(stockRepository).save(stockEntity);
    }

    @Test
    void testCrearStock_ProductoNoExiste() {
        CreateStockDTO dto = new CreateStockDTO(99, 5, 1);

        when(productoClient.obtenerProductoPorId(99L)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> stockService.crearStock(dto));
        verify(stockRepository, never()).save(any());
    }

    @Test
    void testObtenerPorId_Success() {
        Stock stock = new Stock();
        stock.setId(1); stock.setProductId(1); stock.setQuantity(5); stock.setWareHouseId(2);

        ResponseStockDTO response = new ResponseStockDTO();
        response.setId(1); response.setProductId(1); response.setQuantity(5); response.setWareHouseId(2);

        when(stockRepository.findById(1)).thenReturn(Optional.of(stock));
        when(stockMapper.toResponse(stock)).thenReturn(response);

        ResponseStockDTO result = stockService.obtenerPorId(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(stockRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> stockService.obtenerPorId(1));
    }

    @Test
    void testObtenerPorProducto_Success() {
        Stock stock = new Stock();
        stock.setId(1);
        stock.setProductId(1);
        stock.setQuantity(10);

        ResponseStockDTO dto = new ResponseStockDTO();
        dto.setId(1);
        dto.setProductId(1);
        dto.setQuantity(10);

        // Simular que el producto existe en el cliente
        ResponseProductDTO producto = new ResponseProductDTO(
                1L,
                "Mouse Gamer",
                BigDecimal.valueOf(150),
                new ResponseCategoriaDTO(2L, "Accesorios")
        );

        ApiResponse<ResponseProductDTO> apiResponse = new ApiResponse<>();
        apiResponse.setData(producto);
        apiResponse.setStatus(200);
        apiResponse.setMessage("ok");
        apiResponse.setTimestamp("2025-09-12T20:00:00");

        when(productoClient.obtenerProductoPorId(1L)).thenReturn(apiResponse);
        when(stockRepository.findByProductId(1)).thenReturn(List.of(stock));
        when(stockMapper.toResponseList(List.of(stock))).thenReturn(List.of(dto));

        List<ResponseStockDTO> result = stockService.obtenerPorProducto(1);

        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getQuantity());
    }


    @Test
    void testObtenerPorProducto_ProductoNoExiste() {
        when(productoClient.obtenerProductoPorId(99L)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> stockService.obtenerPorProducto(99));
    }

    @Test
    void testActualizarCantidad_Success() {
        Stock stock = new Stock();
        stock.setId(1); stock.setQuantity(5);

        ResponseStockDTO response = new ResponseStockDTO();
        response.setId(1); response.setQuantity(20);

        when(stockRepository.findById(1)).thenReturn(Optional.of(stock));
        when(stockRepository.save(stock)).thenReturn(stock);
        when(stockMapper.toResponse(stock)).thenReturn(response);

        ResponseStockDTO result = stockService.actualizarCantidad(1, 20);

        assertEquals(20, result.getQuantity());
        verify(stockRepository).save(stock);
    }

    @Test
    void testActualizarCantidad_NoExiste() {
        when(stockRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> stockService.actualizarCantidad(1, 10));
    }

    @Test
    void testEliminarStock_Success() {
        Stock stock = new Stock();
        stock.setId(1);

        when(stockRepository.findById(1)).thenReturn(Optional.of(stock));
        doNothing().when(stockRepository).delete(stock);

        assertDoesNotThrow(() -> stockService.eliminarStock(1));
        verify(stockRepository).delete(stock);
    }

    @Test
    void testEliminarStock_NoExiste() {
        when(stockRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> stockService.eliminarStock(1));
    }

    @Test
    void testListarTodos() {
        Stock s1 = new Stock(); s1.setId(1); s1.setQuantity(5);
        Stock s2 = new Stock(); s2.setId(2); s2.setQuantity(8);

        ResponseStockDTO d1 = new ResponseStockDTO(); d1.setId(1); d1.setQuantity(5);
        ResponseStockDTO d2 = new ResponseStockDTO(); d2.setId(2); d2.setQuantity(8);

        when(stockRepository.findAll()).thenReturn(List.of(s1, s2));
        when(stockMapper.toResponseList(List.of(s1, s2))).thenReturn(List.of(d1, d2));

        List<ResponseStockDTO> result = stockService.listarTodos();

        assertEquals(2, result.size());
    }
}
