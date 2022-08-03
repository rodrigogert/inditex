package com.inditex.brands.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.inditex.brands.application.exception.PriceNotFoundException;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handle(PriceNotFoundException ex) {
        return new ResponseEntity<>(ApiErrorResponse.builder().message(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @Builder
    @NonNull
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class ApiErrorResponse {
        @JsonProperty
        String message;
    }
}
