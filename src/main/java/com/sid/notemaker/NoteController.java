package com.sid.notemaker;

import com.sid.notemaker.dto.NoteRequestDTO;
import com.sid.notemaker.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService service;

    public NoteController (NoteService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String hello () {
        return service.hello();
    }

    @GetMapping("/notes")
    public List<Note> getNotes () {
        return service.getNotes();
    }

    @PostMapping("/post")
    public String postNote (@RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        return service.postNote(noteRequestDTO);
    }

    @GetMapping("/notes/{id}")
    public Note getNotesById (@PathVariable int id) {
        return service.getNotesById(id);
    }

    @PutMapping("/notes/{id}")
    public void updateNote(@PathVariable int id, @RequestBody @Valid NoteRequestDTO noteRequestDTO) {
        service.updateNote(id, noteRequestDTO);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote (@PathVariable int id) {
        service.deleteNote(id);
    }
}
