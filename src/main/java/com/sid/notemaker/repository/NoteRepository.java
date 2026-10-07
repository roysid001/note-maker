package com.sid.notemaker.repository;

import com.sid.notemaker.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}
