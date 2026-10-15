package com.sid.notemaker.service;

import com.sid.notemaker.Note;
import com.sid.notemaker.dto.NoteRequestDTO;
import com.sid.notemaker.exception.NoteNotFoundException;
import com.sid.notemaker.repository.NoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository repository;

    public NoteService (NoteRepository repository) {
        this.repository = repository;
    }

    public Page<Note> getNotes (int page, int size, String sortBy, String direction) {
        List<String> allowedFields = List.of("id", "title", "createdAt", "updatedAt");

        if (page < 0) {
            throw new IllegalArgumentException("Page cannot be negative");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive");
        }
        if (!allowedFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }
        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("Invalid sort direction: " + direction);
        }

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return repository.findAll(pageable);
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
