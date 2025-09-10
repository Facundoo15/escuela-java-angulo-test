package com.nttdata.dockerized.postgresql.controller;

import com.nttdata.dockerized.postgresql.model.dto.CreateProductoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseProductDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateProductoDTO;
import com.nttdata.dockerized.postgresql.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseProductDTO crear(@RequestBody @Valid CreateProductoDTO dto) {
        return productoService.crearProducto(dto);
    }

    @GetMapping
    public List<ResponseProductDTO> listar() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public ResponseProductDTO obtener(@PathVariable Long id) {
        return productoService.obtenerProductoId(id);
    }

    @PutMapping("/{id}")
    public ResponseProductDTO actualizar(@PathVariable Long id, @RequestBody @Valid UpdateProductoDTO dto) {
        return productoService.actualizarProducto(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
    }
}
