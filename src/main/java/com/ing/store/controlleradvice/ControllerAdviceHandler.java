package com.ing.store.controlleradvice;

import com.ing.store.dto.ApiError;
import com.ing.store.exceptions.ProductException;
import com.ing.store.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public class ControllerAdviceHandler {

   @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ProductNotFoundException ex) {
        log.error("Resource not found error: {}", ex.getMessage());
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ProductException ex) {
        log.error("Product error: {}", ex.getMessage());
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleResourceNotFound(Exception ex) {
        log.error("General error: {}", ex.getMessage());
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
