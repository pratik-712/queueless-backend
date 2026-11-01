package com.queueless.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoWaitingCustomersException.class)
    public ResponseEntity<Map<String, String>> handleNoWaitingCustomers(
            NoWaitingCustomersException exception) {

        Map<String, String> response = Map.of(
                "message", exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Map<String, String>> handleIllegalArgument(
                IllegalArgumentException exception) {

        Map<String, String> response =
                Map.of("message", exception.getMessage());

        return ResponseEntity.badRequest().body(response);
        }

        @ExceptionHandler(QueueNotFoundException.class)
        public ResponseEntity<Map<String, String>> handleQueueNotFound(
                QueueNotFoundException exception) {

        Map<String, String> response =
                Map.of("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        @ExceptionHandler(TokenNotFoundException.class)
        public ResponseEntity<Map<String, String>> handleTokenNotFound(
                TokenNotFoundException exception) {

        Map<String, String> response =
                Map.of("message", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
}