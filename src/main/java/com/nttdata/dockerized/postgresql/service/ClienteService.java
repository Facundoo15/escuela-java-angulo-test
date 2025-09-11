package com.nttdata.dockerized.postgresql.service;


import com.nttdata.dockerized.postgresql.model.dto.CreateClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateClienteDTO;
import com.nttdata.dockerized.postgresql.model.entity.Cliente;

import java.util.List;

public interface ClienteService {
    ResponseClienteDTO crearCliente(CreateClienteDTO createClienteDTO);
    List<ResponseClienteDTO> listarClientes();
    ResponseClienteDTO obtenerClienteId(Long id);
    Cliente obtenerEntidadId(Long id);
    ResponseClienteDTO actualizarCliente(Long id, UpdateClienteDTO updateClienteDTO);
    void eliminarCliente(Long id);
}
