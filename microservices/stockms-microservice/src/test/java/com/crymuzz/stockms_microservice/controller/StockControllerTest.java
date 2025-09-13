package com.crymuzz.stockms_microservice.controller;

import com.crymuzz.stockms_microservice.model.dto.CreateStockDTO;
import com.crymuzz.stockms_microservice.model.dto.ResponseStockDTO;
import com.crymuzz.stockms_microservice.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StockController.class)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearStock() throws Exception {
        CreateStockDTO dto = new CreateStockDTO(1, 10, 2);
        ResponseStockDTO response = new ResponseStockDTO(1, 1, 10, 2);

        when(stockService.crearStock(any(CreateStockDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/stocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.quantity").value(2));
    }


    @Test
    void testObtenerPorId() throws Exception {
        ResponseStockDTO response = new ResponseStockDTO(1, 1, 10, 2);

        when(stockService.obtenerPorId(1))
                .thenReturn(response);

        mockMvc.perform(get("/api/stocks/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.productId").value(1));

    }


    @Test
    void testObtenerPorProducto() throws Exception {
        ResponseStockDTO r1 = new ResponseStockDTO();
        r1.setId(1); r1.setProductId(1); r1.setQuantity(10); r1.setWareHouseId(2);
        ResponseStockDTO r2 = new ResponseStockDTO();
        r2.setId(2); r2.setProductId(1); r2.setQuantity(5); r2.setWareHouseId(3);

        List<ResponseStockDTO> responseList = List.of(r1, r2);

        when(stockService.obtenerPorProducto(1)).thenReturn(responseList);

        mockMvc.perform(get("/api/stocks/producto/{productId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))    // el array está en data
                .andExpect(jsonPath("$.data[0].productId").value(1));
    }

    @Test
    void testActualizarCantidad() throws Exception {
        ResponseStockDTO response = new ResponseStockDTO();
        response.setId(1); response.setProductId(1); response.setQuantity(20); response.setWareHouseId(2);

        when(stockService.actualizarCantidad(eq(1), eq(20))).thenReturn(response);

        mockMvc.perform(put("/api/stocks/{id}/cantidad", 1)
                        .param("nuevaCantidad", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.quantity").value(20));
    }
}
