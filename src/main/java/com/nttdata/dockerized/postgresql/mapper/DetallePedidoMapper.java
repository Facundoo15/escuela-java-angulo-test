package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.CreateDetallePedidoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseDetallePedidoDTO;
import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductoMapper.class})
public interface DetallePedidoMapper {
    DetallePedido toEntity(CreateDetallePedidoDTO createPedidoDTO);
    ResponseDetallePedidoDTO toDto(DetallePedido detalle);
    List<ResponseDetallePedidoDTO> toDtoList(List<DetallePedido> detalles);
}
