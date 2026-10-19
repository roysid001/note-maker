package com.sid.notemaker;

import com.sid.notemaker.dto.NoteRequestDTO;
import com.sid.notemaker.repository.NoteRepository;
import com.sid.notemaker.service.NoteService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		Note existingNote = new Note(
				3, "GCP", "Google Cloud Platform", LocalDateTime.now(), LocalDateTime.now()
		);

		when(repository.findById(3))
				.thenReturn(Optional.of(existingNote));

		when(repository.save(any(Note.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		NoteRequestDTO noteRequestDTO = new NoteRequestDTO(
				"GCPD", "Gotham City Police Department"
		);

		noteService.updateNote(3, noteRequestDTO);

		ArgumentCaptor<Note> captor =
				ArgumentCaptor.forClass(Note.class);

		verify(repository).save(captor.capture());

		Note updatedNote = captor.getValue();

		assertEquals("GCPD", updatedNote.getTitle());
		assertEquals("Gotham City Police Department", updatedNote.getContent());
	}

	@Test
	void deleteNote() {
		Note existingNote = new Note(
				3, "GCP", "Google Cloud Platform", LocalDateTime.now(), LocalDateTime.now()
		);

		when(repository.findById(3))
				.thenReturn(Optional.of(existingNote));

		noteService.deleteNote(3);

		verify(repository).delete(existingNote);
	}
}
