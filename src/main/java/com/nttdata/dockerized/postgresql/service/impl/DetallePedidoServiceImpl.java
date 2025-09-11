package com.nttdata.dockerized.postgresql.service.impl;

import com.nttdata.dockerized.postgresql.mapper.DetallePedidoMapper;
import com.nttdata.dockerized.postgresql.model.dto.ResponseDetallePedidoDTO;
import com.nttdata.dockerized.postgresql.model.entity.DetallePedido;
import com.nttdata.dockerized.postgresql.repository.DetallePedidoRepository;
import com.nttdata.dockerized.postgresql.service.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;
    private final DetallePedidoMapper detallePedidoMapper;

    @Override
    public List<ResponseDetallePedidoDTO> listarPorPedidoId(Long pedidoId) {
        List<DetallePedido> detalles = detallePedidoRepository.findByPedidoId(pedidoId);
        return detallePedidoMapper.toDtoList(detalles);
    }
}
