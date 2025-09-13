package com.crymuzz.stockms_microservice.mapper;

import com.crymuzz.stockms_microservice.model.dto.CreateStockDTO;
import com.crymuzz.stockms_microservice.model.dto.ResponseStockDTO;
import com.crymuzz.stockms_microservice.model.entity.Stock;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockMapperTest {

    private final StockMapper mapper = Mappers.getMapper(StockMapper.class);

    @Test
    void testToEntity() {
        CreateStockDTO dto = new CreateStockDTO(1, 20, 2);

        Stock entity = mapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getProductId(), entity.getProductId());
        assertEquals(dto.getQuantity(), entity.getQuantity());
        assertEquals(dto.getWareHouseId(), entity.getWareHouseId());
    }

    @Test
    void testToResponse() {
        Stock entity = new Stock(1, 1, 15, 3);

        ResponseStockDTO response = mapper.toResponse(entity);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
        assertEquals(entity.getProductId(), response.getProductId());
        assertEquals(entity.getQuantity(), response.getQuantity());
        assertEquals(entity.getWareHouseId(), response.getWareHouseId());
    }

    @Test
    void testToResponseList() {
        Stock entity1 = new Stock(1, 1, 15, 3);
        Stock entity2 = new Stock(2, 2, 30, 4);

        List<ResponseStockDTO> responseList = mapper.toResponseList(List.of(entity1, entity2));

        assertNotNull(responseList);
        assertEquals(2, responseList.size());
        assertEquals(1, responseList.get(0).getId());
        assertEquals(2, responseList.get(1).getId());
    }
}
