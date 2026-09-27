package com.sid.notemaker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (NoteNotFoundException.class)
    public ResponseEntity<String> handleNoteNotFound (NoteNotFoundException e) {
        return ResponseEntity
                .status(404)
                .body(e.getMessage());
    }
}
