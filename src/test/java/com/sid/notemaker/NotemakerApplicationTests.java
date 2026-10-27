package com.sid.notemaker;

import com.sid.notemaker.dto.NoteRequestDTO;
import com.sid.notemaker.repository.NoteRepository;
import com.sid.notemaker.service.NoteService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class NotemakerApplicationTests {

	@Autowired
	NoteRepository repository;
    @Autowired
    private NoteService noteService;

	@Test
	void contextLoads() {
	}

	@Test
	void createNote () {
		Note note = new Note(null, "Kafka", "Learning Kafka", LocalDateTime.now(), LocalDateTime.now());
		Note savedNote = repository.save(note);
		Optional<Note> retrievedNote = repository.findById(savedNote.getId());

		assertTrue(retrievedNote.isPresent() , "Passed");
		assertEquals("Kafka", retrievedNote.get().getTitle());
		assertEquals("Learning Kafka", retrievedNote.get().getContent());
	}

	@Test
	void updateNote() {
		Note existingNote = repository.save(
				new Note(null, "GCP", "Google Cloud Platform",
						LocalDateTime.now(), LocalDateTime.now())
		);

		NoteRequestDTO noteRequestDTO = new NoteRequestDTO(
				"GCPD", "Gotham City Police Department"
		);

		noteService.updateNote(existingNote.getId(), noteRequestDTO);

		Note updatedNote = repository.findById(existingNote.getId())
				.orElseThrow();

		assertEquals("GCPD", updatedNote.getTitle());
		assertEquals("Gotham City Police Department", updatedNote.getContent());
	}

	@Test
	void deleteNote() {
		Note existingNote = repository.save(
				new Note(null, "GCP", "Google Cloud Platform",
						LocalDateTime.now(), LocalDateTime.now())
		);

		Integer id = existingNote.getId();

		noteService.deleteNote(id);

		assertTrue(repository.findById(id).isEmpty());
	}
}
