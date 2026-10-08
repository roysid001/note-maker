package com.sid.notemaker;

import com.sid.notemaker.dto.NoteRequestDTO;
import com.sid.notemaker.service.NoteService;
import jakarta.validation.Valid;
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
    public List<Note> getNotes () {
        return service.getNotes();
    }

    @PostMapping("/notes")
    public ResponseEntity<Void> postNote (@RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        service.postNote(noteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/notes/{id}")
    public Note getNotesById (@PathVariable int id) {
        return service.getNotesById(id);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Void> updateNote(@PathVariable int id, @RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        service.updateNote(id, noteRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote (@PathVariable int id) {
        service.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
