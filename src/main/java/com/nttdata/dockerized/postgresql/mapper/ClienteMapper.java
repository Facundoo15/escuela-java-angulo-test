package com.nttdata.dockerized.postgresql.mapper;

import com.nttdata.dockerized.postgresql.model.dto.CreateClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseClienteDTO;
import com.nttdata.dockerized.postgresql.model.entity.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toEntity(CreateClienteDTO dto);
    ResponseClienteDTO toDto(Cliente cliente);
    List<ResponseClienteDTO> toDtoList(List<Cliente> clientes);
}
