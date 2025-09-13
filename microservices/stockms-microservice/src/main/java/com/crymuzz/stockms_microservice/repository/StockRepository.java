package com.crymuzz.stockms_microservice.repository;

import com.crymuzz.stockms_microservice.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProductId(Integer productId);
}