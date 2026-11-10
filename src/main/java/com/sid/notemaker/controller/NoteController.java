package com.sid.notemaker.controller;

import com.sid.notemaker.dto.NoteRequestDTO;
import com.sid.notemaker.dto.NoteResponseDTO;
import com.sid.notemaker.service.NoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService service;

    public NoteController (NoteService service) {
        this.service = service;
    }

    @GetMapping("/notes")
    public Page<NoteResponseDTO> getNotes (
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue="createdAt") String sortBy,
        @RequestParam(defaultValue="asc") String direction
    ) {
        return service.getNotes(page, size, sortBy, direction);
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteResponseDTO> postNote (@RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        NoteResponseDTO createdNote = service.postNote(noteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @GetMapping("/notes/search")
    public List<NoteResponseDTO> searchNotes (@RequestParam @NotBlank String keyword) {
        return service.searchNotes(keyword);
    }

    @GetMapping("/notes/{id}")
    public NoteResponseDTO getNotesById (@PathVariable int id) {
        return service.getNoteById(id);
    }

    @PutMapping("/notes/{id}")
    public NoteResponseDTO updateNote(@PathVariable int id, @RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        return service.updateNote(id, noteRequestDTO);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote (@PathVariable int id) {
        service.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
