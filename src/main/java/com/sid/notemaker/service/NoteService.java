package com.sid.notemaker.service;

import com.sid.notemaker.Note;
import com.sid.notemaker.dto.NoteRequestDTO;
import com.sid.notemaker.exception.NoteNotFoundException;
import com.sid.notemaker.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService (NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getNotes () {
        return repository.findAll();
    }

    public void postNote (NoteRequestDTO noteRequestDTO) {
        LocalDateTime now = LocalDateTime.now();
        Note note = new Note(
                null,
                noteRequestDTO.getTitle(),
                noteRequestDTO.getContent(),
                now,
                now
        );
        repository.save(note);
    }

    public Note getNotesById (int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }

    public void updateNote(int id, NoteRequestDTO noteRequestDTO) {
        Note note = repository
                .findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
        note.setTitle(noteRequestDTO.getTitle());
        note.setContent(noteRequestDTO.getContent());
        note.setUpdatedAt(LocalDateTime.now());

        repository.save(note);
    }

    public void deleteNote (int id) {
        Note note = repository
                .findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));

        repository.delete(note);
    }

    public List<Note> searchNotes (String keyword) {
        return repository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }
}
