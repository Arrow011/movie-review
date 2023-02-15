package com.project.movies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorDetails> movieNotFoundException(MovieNotFoundException movieNotFoundException, WebRequest request) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(movieNotFoundException.getMessage())
                .details(request.getDescription(false))
                .timestamp(new Date())
                .build();
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalException(Exception exception, WebRequest request){
        ErrorDetails errorDetails = ErrorDetails.builder()
                .message(exception.getMessage())
                .details(request.getDescription(true))
                .timestamp(new Date())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
