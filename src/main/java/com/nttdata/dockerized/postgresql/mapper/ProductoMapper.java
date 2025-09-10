package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.CreateProductoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseProductDTO;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses = {CategoriaMapper.class})
public interface ProductoMapper {

    Producto toEntity(CreateProductoDTO dto);

    ResponseProductDTO toDto(Producto producto);

    List<ResponseProductDTO> toDtoList(List<Producto> productos);
}
