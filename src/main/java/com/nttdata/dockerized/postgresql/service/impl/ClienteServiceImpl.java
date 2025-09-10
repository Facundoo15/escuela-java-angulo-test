package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.mapper.ClienteMapper;
import com.nttdata.dockerized.postgresql.model.dto.CreateClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateClienteDTO;
import com.nttdata.dockerized.postgresql.model.entity.Cliente;
import com.nttdata.dockerized.postgresql.repository.ClienteRepository;
import com.nttdata.dockerized.postgresql.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional
    public ResponseClienteDTO crearCliente(CreateClienteDTO createClienteDTO) {
        boolean existe = clienteRepository.existsByEmail(createClienteDTO.getEmail());
        if (existe)
            throw new BadRequestException("Ya existe un cliente con ese email");

        Cliente cliente = clienteMapper.toEntity(createClienteDTO);
        cliente.setFechaRegistro(LocalDate.now());
        cliente.setActivo(true);

        cliente = clienteRepository.save(cliente);
        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public List<ResponseClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDtoList(clientes);
    }

    @Override
    @Transactional
    public ResponseClienteDTO obtenerClienteId(Long id) {
        Cliente cliente = obtenerEntidadId(id);
        return clienteMapper.toDto(cliente);
    }

    @Override
    @Transactional
    public Cliente obtenerEntidadId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
    }

    @Override
    @Transactional
    public ResponseClienteDTO actualizarCliente(Long id, UpdateClienteDTO updateClienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        cliente.setNombre(updateClienteDTO.getNombre());
        cliente.setEmail(updateClienteDTO.getEmail());

        Cliente actualizado = clienteRepository.save(cliente);
        return clienteMapper.toDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
        clienteRepository.delete(cliente);
    }

}
