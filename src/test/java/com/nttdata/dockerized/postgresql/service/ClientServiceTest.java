package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.exception.BadRequestException;
import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.mapper.ClienteMapper;
import com.nttdata.dockerized.postgresql.model.dto.CreateClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponseClienteDTO;
import com.nttdata.dockerized.postgresql.model.dto.UpdateClienteDTO;
import com.nttdata.dockerized.postgresql.model.entity.Cliente;
import com.nttdata.dockerized.postgresql.repository.ClienteRepository;
import com.nttdata.dockerized.postgresql.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ClienteMapper clienteMapper;
    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;

    // Mis casos correctos
    // Crear
    @Test
    void testCrearClienteSuccess() {
        CreateClienteDTO dto = new CreateClienteDTO();
        dto.setNombre("Favio Angulo");
        dto.setEmail("favioangulo2002@gmail.com");

        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());

        when(clienteRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(clienteMapper.toEntity(dto)).thenReturn(cliente);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDto(cliente)).thenReturn(new ResponseClienteDTO());

        ResponseClienteDTO result = clienteServiceImpl.crearCliente(dto);

        assertNotNull(result);
        assertNotNull(cliente.getFechaRegistro());
        verify(clienteRepository, times(1)).save(cliente);
    }

    // Buscar por id
    @Test
    void testObtenerClientePorIdSuccess() {
        Cliente cliente = new Cliente(1L, "Favio Angulo", "favioangulo2002@gmail.com", LocalDate.now(), true);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(clienteMapper.toDto(cliente)).thenReturn(new ResponseClienteDTO());

        ResponseClienteDTO result = clienteServiceImpl.obtenerClienteId(1L);

        assertNotNull(result);
        verify(clienteRepository, times(1)).findById(1L);
    }

    // Actualizar
    @Test
    void testActualizarClienteSuccess() {
        Cliente cliente = new Cliente(1L, "Favio Angulo", "favioangulo2002@gmail.com", LocalDate.now(), true);

        UpdateClienteDTO updateDto = new UpdateClienteDTO();
        updateDto.setNombre("Juan Actualizado");
        updateDto.setEmail("juanactual@mail.com");
        updateDto.setActivo(true);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        when(clienteMapper.toDto(cliente)).thenReturn(new ResponseClienteDTO());

        ResponseClienteDTO result = clienteServiceImpl.actualizarCliente(1L, updateDto);

        assertNotNull(result);
        assertEquals(updateDto.getNombre(), cliente.getNombre());
        assertEquals(updateDto.getEmail(), cliente.getEmail());
        verify(clienteRepository, times(1)).save(cliente);
    }

    // Eliminar
    @Test
    void testEliminarClienteSuccess() {
        Cliente cliente = new Cliente(1L, "Favio Angulo", "favioangulo2002@gmail.com", LocalDate.now(), true);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);

        assertDoesNotThrow(() -> clienteServiceImpl.eliminarCliente(1L));
        verify(clienteRepository, times(1)).delete(cliente);
    }

    // Casos de error
    // Email existe
    @Test
    void testClienteEmailExiste() {
        CreateClienteDTO dto = new CreateClienteDTO();
        dto.setNombre("Favio Angulo");
        dto.setEmail("favioangulo2002@gmail.com");

        when(clienteRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(BadRequestException.class,
                () -> clienteServiceImpl.crearCliente(dto));
        verify(clienteRepository, never()).save(any());
    }

    // No existe
    @Test
    void testClienteNoExiste() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> clienteServiceImpl.obtenerClienteId(1L));
        verify(clienteRepository, times(1)).findById(1L);
    }


}
