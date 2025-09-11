package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.CreateCategoriaDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseCategoriaDTO;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toEntity(CreateCategoriaDTO dto);

    ResponseCategoriaDTO toDto(Categoria categoria);

    List<ResponseCategoriaDTO> toDtoList(List<Categoria> categorias);
}
