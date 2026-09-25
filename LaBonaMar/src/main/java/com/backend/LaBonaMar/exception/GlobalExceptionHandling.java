package com.backend.LaBonaMar.exception;

import com.backend.LaBonaMar.util.APIResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
    public ResponseEntity<APIResponseMessage> resourceNotFoundHandler(ResourceNotFound resourceNotFound){
        APIResponseMessage apiResponseMessage = APIResponseMessage
                .builder()
                .message(resourceNotFound.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .data(null)
                .success(true)
                .build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.NOT_FOUND);
    }
}
