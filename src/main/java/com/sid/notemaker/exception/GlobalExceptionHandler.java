package com.sid.notemaker.exception;

import com.sid.notemaker.response.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (NoteNotFoundException.class)
    public ResponseEntity<ApiError> handleNoteNotFound (NoteNotFoundException e) {
        ApiError apiError = new ApiError(404, "Not Found", e.getMessage());
        return ResponseEntity
                .status(404)
                .body(apiError);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        ApiError apiError = new ApiError(400, "Bad Request", message);
        return ResponseEntity
                .badRequest()
                .body(apiError);
    }

    @ExceptionHandler (IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException (IllegalArgumentException e) {
        ApiError apiError = new ApiError(400, "Bad Request", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(apiError);
    }

    @ExceptionHandler (HandlerMethodValidationException.class)
    public ResponseEntity<ApiError> handleSearchValidation (HandlerMethodValidationException e) {
        ApiError apiError = new ApiError(400, "Bad Request", "Search keyword must not be blank");
        return ResponseEntity
                .badRequest()
                .body(apiError);
    }
}
