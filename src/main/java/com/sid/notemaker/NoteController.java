package com.sid.notemaker;

import com.sid.notemaker.service.NoteService;
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
    public String postNote (@RequestBody Note note) {
        return service.postNote(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNotesById (@PathVariable int id) {
        return service.getNotesById(id);
    }

    @PutMapping("/notes/{id}")
    public void updateNote(@PathVariable int id, @RequestBody Note note) {
        service.updateNote(id, note);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote (@PathVariable int id) {
        service.deleteNote(id);
    }
}
