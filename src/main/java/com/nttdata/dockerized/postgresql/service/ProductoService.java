package com.nttdata.dockerized.postgresql.service;


import com.nttdata.dockerized.postgresql.model.dto.CreateProductoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseProductDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateProductoDTO;
import com.nttdata.dockerized.postgresql.model.entity.Producto;

import java.util.List;

public interface ProductoService {
    ResponseProductDTO crearProducto(CreateProductoDTO createProductoDTO);
    List<ResponseProductDTO> listarProductos();
    ResponseProductDTO obtenerProductoId(Long id);
    Producto obtenerEntidadId(Long id);
    ResponseProductDTO actualizarProducto(Long id,UpdateProductoDTO updateProductoDTO);
    void eliminarProducto(Long id);
}
