package com.crymuzz.stockms_microservice.entity;

import static org.assertj.core.api.Assertions.assertThat;
import com.crymuzz.stockms_microservice.model.entity.Stock;
import org.junit.jupiter.api.Test;

class StockTest {

    @Test
    void testStockGettersAndSetters() {
        Stock stock = new Stock();
        stock.setId(1);
        stock.setProductId(100);
        stock.setWareHouseId(10);
        stock.setQuantity(50);

        assertThat(stock.getId()).isEqualTo(1);
        assertThat(stock.getProductId()).isEqualTo(100);
        assertThat(stock.getWareHouseId()).isEqualTo(10);
        assertThat(stock.getQuantity()).isEqualTo(50);
    }

    @Test
    void testStockAllArgsConstructor() {
        Stock stock = new Stock(1, 100, 10, 50);

        assertThat(stock.getId()).isEqualTo(1);
        assertThat(stock.getProductId()).isEqualTo(100);
        assertThat(stock.getWareHouseId()).isEqualTo(10);
        assertThat(stock.getQuantity()).isEqualTo(50);
    }
}