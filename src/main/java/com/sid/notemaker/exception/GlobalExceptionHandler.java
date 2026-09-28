package com.sid.notemaker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (NoteNotFoundException.class)
    public ResponseEntity<String> handleNoteNotFound (NoteNotFoundException e) {
        return ResponseEntity
                .status(404)
                .body(e.getMessage());
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException (MethodArgumentNotValidException e) {
        BindingResult r = e.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        r.getFieldErrors().forEach(error -> errors.put(
                error.getField(),
                error.getDefaultMessage()
        ));
        return ResponseEntity
                .status(400)
                .body(errors);
    }
}
