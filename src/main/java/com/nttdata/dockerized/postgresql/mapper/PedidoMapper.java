package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.CreatePedidoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponsePedidoDTO;
import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class, DetallePedidoMapper.class})
public interface PedidoMapper {
    Pedido toEntity(CreatePedidoDTO createPedidoDTO);
    ResponsePedidoDTO toDto(Pedido pedido);
    List<ResponsePedidoDTO> toDtoList(List<Pedido> pedidos);
}
