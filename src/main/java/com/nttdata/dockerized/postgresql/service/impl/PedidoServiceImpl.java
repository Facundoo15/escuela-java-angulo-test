package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.exception.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.mapper.DetallePedidoMapper;
import com.nttdata.dockerized.postgresql.mapper.PedidoMapper;
import com.nttdata.dockerized.postgresql.model.dto.CreatePedidoDTO;
import com.nttdata.dockerized.postgresql.model.dto.ResponsePedidoDTO;
import com.nttdata.dockerized.postgresql.model.entity.Cliente;
import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import com.nttdata.dockerized.postgresql.model.entity.Pedido;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.model.enums.EstadoPedido;
import com.nttdata.dockerized.postgresql.repository.ClienteRepository;
import com.nttdata.dockerized.postgresql.repository.PedidoRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import com.nttdata.dockerized.postgresql.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoMapper pedidoMapper;
    private final DetallePedidoMapper detallePedidoMapper;

    @Transactional
    public ResponsePedidoDTO crearPedido(CreatePedidoDTO createPedidoDTO) {
        Cliente cliente = clienteRepository.findById(createPedidoDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        Pedido pedido = pedidoMapper.toEntity(createPedidoDTO);
        pedido.setCliente(cliente);
        pedido.setFechaPedido(createPedidoDTO.getFechaPedido());
        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedido.setTotal(BigDecimal.ZERO);

        List<DetallePedido> detalles = createPedidoDTO.getDetalles().stream()
                .map(detalleDTO -> {
                    DetallePedido detalle = detallePedidoMapper.toEntity(detalleDTO);


                    Producto producto = productoRepository.findById(detalleDTO.getProductoId())
                            .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
                    detalle.setProducto(producto);


                    detalle.setPedido(pedido);

                    BigDecimal precioUnitario = producto.getPrecio();
                    detalle.setPrecioUnitario(precioUnitario);
                    detalle.setTotalDetalle(precioUnitario.multiply(BigDecimal.valueOf(detalle.getCantidad())));

                    return detalle;
                }).toList();
        pedido.setDetalles(detalles);
        BigDecimal totalPedido = detalles.stream()
                .map(DetallePedido::getTotalDetalle)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setTotal(totalPedido);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        return pedidoMapper.toDto(pedidoGuardado);
    }


    private BigDecimal obtenerPrecioProducto(Long productoId) {
        return productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"))
                .getPrecio();
    }

    @Override
    public List<ResponsePedidoDTO> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoMapper.toDtoList(pedidos);
    }

    @Override
    public ResponsePedidoDTO obtenerPedidoId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido no encontrado"));
        return pedidoMapper.toDto(pedido);
    }

}
