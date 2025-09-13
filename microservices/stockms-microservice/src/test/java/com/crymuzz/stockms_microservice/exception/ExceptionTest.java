package com.crymuzz.stockms_microservice.exception;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExceptionTest {

    @Test
    void testBadRequestExceptionMessage() {
        assertThatThrownBy(() -> { throw new BadRequestException("Error de request"); })
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Error de request");
    }

    @Test
    void testResourceNotFoundExceptionMessage() {
        assertThatThrownBy(() -> { throw new ResourceNotFoundException("Recurso no encontrado"); })
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Recurso no encontrado");
    }
}
