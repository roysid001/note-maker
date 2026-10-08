package com.sid.notemaker.repository;

import com.sid.notemaker.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase (
            String titleKeyword,
            String contentKeyword
    );
}
