package com.crymuzz.stockms_microservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStockDTO {
    private Integer id;
    private Integer productId;
    private Integer wareHouseId;
    private Integer quantity;
}
