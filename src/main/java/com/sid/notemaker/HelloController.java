package com.sid.notemaker;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    List<Note> notes = new ArrayList<>();
    int id = 1;

    public  HelloController () {
        notes.add(new Note(id++, "Java", "Learning Java"));
        notes.add(new Note(id++, "Spring Boot", "Learning Spring Boot"));
        notes.add(new Note(id++, "PostgreSql", "Learning PostgreSql"));
    }

    @GetMapping("/")
    public String hello () {
        return "Hello from Note Maker!";
    }

    @GetMapping("/notes")
    public List<Note> getNotes () {
        return notes;
    }

    @PostMapping("/post")
    public String postNote (@RequestBody Note note) {
        System.out.println(note.getTitle());
        System.out.println(note.getContent());

        notes.add(new Note(id++, note.getTitle(), note.getContent()));
        return "Note "+note.getTitle()+" created with content: "+note.getContent();
    }

    @GetMapping("/notes/{id}")
    public Note getNotesById (@PathVariable int id) {
        Note noteForId = null;
        for (Note note : notes) {
            if (note.getId() == id) {
                noteForId = note;
                break;
            }
        }
        return noteForId;
    }

    @PutMapping("/notes/{id}")
    public void updateNote(@PathVariable int id, @RequestBody Note note) {
        Note noteForId = null;
        for (Note n: notes) {
            if (n.getId() == id) {
                noteForId = n;
                break;
            }
        }
        noteForId.setTitle(note.getTitle());
        noteForId.setContent(note.getContent());
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote (@PathVariable int id) {
        Note noteById = null;
        for (Note n : notes) {
            if (n.getId() == id) {
                noteById = n;
                break;
            }
        }
        notes.remove(noteById);
    }
}
