package com.crymuzz.stockms_microservice.mapper;

import com.crymuzz.stockms_microservice.model.dto.CreateStockDTO;
import com.crymuzz.stockms_microservice.model.dto.ResponseStockDTO;
import com.crymuzz.stockms_microservice.model.entity.Stock;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    Stock toEntity(CreateStockDTO dto);
    ResponseStockDTO toResponse(Stock entity);
    List<ResponseStockDTO> toResponseList(List<Stock> entities);
}
