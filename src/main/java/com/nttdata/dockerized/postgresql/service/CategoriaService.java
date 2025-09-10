package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.CreateCategoriaDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseCategoriaDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateCategoriaDTO;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import java.util.List;

public interface CategoriaService {
    ResponseCategoriaDTO crearCategoria(CreateCategoriaDTO createCategoriaDTO);
    List<ResponseCategoriaDTO> listarCategorias();
    ResponseCategoriaDTO obtenerCategoriaId(Long id);
    Categoria obtenerEntidadId(Long id);
    ResponseCategoriaDTO actualizarCategoria(Long id, UpdateCategoriaDTO updateCategoriaDTO);
    void eliminarCategoria(Long id);
}
