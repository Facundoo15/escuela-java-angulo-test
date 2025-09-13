package com.crymuzz.stockms_microservice.client;

import com.crymuzz.stockms_microservice.model.dto.ApiResponse;
import com.crymuzz.stockms_microservice.model.dto.ResponseProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-microservice")
public interface ProductoClient {
    @GetMapping("/api/productos/{id}")
    ApiResponse<ResponseProductDTO> obtenerProductoPorId(@PathVariable Long id);
}