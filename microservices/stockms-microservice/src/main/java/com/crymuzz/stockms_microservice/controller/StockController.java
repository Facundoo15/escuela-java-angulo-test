package com.crymuzz.stockms_microservice.controller;

import com.crymuzz.stockms_microservice.model.dto.CreateStockDTO;
import com.crymuzz.stockms_microservice.model.dto.ResponseStockDTO;
import com.crymuzz.stockms_microservice.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseStockDTO crear(@RequestBody @Valid CreateStockDTO dto) {
        return stockService.crearStock(dto);
    }

    @GetMapping("/{id}")
    public ResponseStockDTO obtenerPorId(@PathVariable Integer id) {
        return stockService.obtenerPorId(id);
    }

    @GetMapping("/producto/{productId}")
    public List<ResponseStockDTO> obtenerPorProducto(@PathVariable Integer productId) {
        return stockService.obtenerPorProducto(productId);
    }

    @PutMapping("/{id}/cantidad")
    public ResponseStockDTO actualizarCantidad(
            @PathVariable Integer id,
            @RequestParam Integer nuevaCantidad
    ) {
        return stockService.actualizarCantidad(id, nuevaCantidad);
    }
}
