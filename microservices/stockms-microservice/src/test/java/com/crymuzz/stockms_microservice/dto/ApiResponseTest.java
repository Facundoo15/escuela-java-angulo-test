package com.crymuzz.stockms_microservice.dto;

import com.crymuzz.stockms_microservice.model.dto.ApiResponse;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void testApiResponseSettersAndGetters() {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus(200);
        response.setMessage("OK");
        response.setData("Mi dato");
        response.setTimestamp("2025-09-12T20:00:00");

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("OK");
        assertThat(response.getData()).isEqualTo("Mi dato");
        assertThat(response.getTimestamp()).isEqualTo("2025-09-12T20:00:00");
    }
}
