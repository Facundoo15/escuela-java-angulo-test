package com.crymuzz.stockms_microservice.exception;

import com.crymuzz.stockms_microservice.model.dto.CustomErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
        request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getRequestURI()).thenReturn("/api/test");
    }

    @Test
    void testHandleNotFoundResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("No encontrado");

        var response = handler.handleNotFound(ex, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        CustomErrorResponse body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMessage()).isEqualTo("No encontrado");
        assertThat(body.getPath()).isEqualTo("/api/test");
        assertThat(body.getStatus()).isEqualTo(404);
    }

    @Test
    void testHandleNotFoundEntityNotFoundException() {
        EntityNotFoundException ex = new EntityNotFoundException("Entidad no encontrada");

        var response = handler.handleNotFound(ex, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        CustomErrorResponse body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getMessage()).isEqualTo("Entidad no encontrada");
    }

    @Test
    void testHandleBadRequest() {
        BadRequestException ex = new BadRequestException("Request inválido");

        var response = handler.handleBadRequest(ex, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
        CustomErrorResponse body = response.getBody();
        assertThat(body.getMessage()).isEqualTo("Request inválido");
        assertThat(body.getStatus()).isEqualTo(400);
    }

    @Test
    void testHandleValidation() {
        MethodArgumentNotValidException ex = Mockito.mock(MethodArgumentNotValidException.class);
        var bindingResult = Mockito.mock(org.springframework.validation.BindingResult.class);
        var fieldError = new org.springframework.validation.FieldError("objectName", "field", "error");
        Mockito.when(ex.getBindingResult()).thenReturn(bindingResult);
        Mockito.when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        var response = handler.handleValidation(ex, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(422);
        CustomErrorResponse body = response.getBody();
        assertThat(body.getMessage()).isEqualTo("Error de validación");
        assertThat(body.getFieldErrors()).containsEntry("field", "error");
    }

    @Test
    void testHandleGeneralException() {
        Exception ex = new Exception("Error inesperado");

        var response = handler.handleGeneral(ex, request);

        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        CustomErrorResponse body = response.getBody();
        assertThat(body.getMessage()).isEqualTo("Error inesperado");
        assertThat(body.getStatus()).isEqualTo(500);
    }
}
