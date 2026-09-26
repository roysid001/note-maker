package com.sid.notemaker.service;

import com.sid.notemaker.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    List<Note> notes = new ArrayList<>();
    int id = 1;

    public NoteService() {
        notes.add(new Note(id++, "Java", "Learning Java"));
        notes.add(new Note(id++, "Spring Boot", "Learning Spring Boot"));
        notes.add(new Note(id++, "PostgreSql", "Learning PostgreSql"));
    }

    public String hello () {
        return "Hello from Note Maker!";
    }

    public List<Note> getNotes () {
        return notes;
    }

    public String postNote (Note note) {
        notes.add(new Note(id++, note.getTitle(), note.getContent()));
        return "Note "+note.getTitle()+" created with content: "+note.getContent();
    }

    public Note getNotesById (int id) {
        Note noteForId = null;
        for (Note note : notes) {
            if (note.getId() == id) {
                noteForId = note;
                break;
            }
        }
        return noteForId;
    }

    public void updateNote(int id, Note note) {
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

    public void deleteNote (int id) {
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
