package com.crymuzz.stockms_microservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStockDTO {
    private Integer productId;
    private Integer quantity;
    private Integer wareHouseId;
}
