package com.sid.notemaker;

import com.sid.notemaker.repository.NoteRepository;
import com.sid.notemaker.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {
    LocalDateTime now = LocalDateTime.now();
    List<Note> notes = List.of(
            new Note(1, "Java", "Learning Java", now, now),
            new Note(2, "SQL", "Learning SQL", now, now)
    );
    Page<Note> expectedPage = new PageImpl<>(notes);

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void getNotes_returnsAllNotes () {
        when(noteRepository.findAll(any(Pageable.class))).thenReturn(expectedPage);
        Page<Note> actualPage = noteService.getNotes(0, 2, "title", "asc");

        assertEquals(2, actualPage.getContent().size());
        assertEquals("Java", actualPage.getContent().get(0).getTitle());
        assertEquals("SQL", actualPage.getContent().get(1).getTitle());

        ArgumentCaptor<Pageable> captor = ArgumentCaptor.forClass(Pageable.class);
        verify(noteRepository).findAll(captor.capture());
        Pageable actualPageable = captor.getValue();
        assertEquals(0, actualPageable.getPageNumber());
        assertEquals(2, actualPageable.getPageSize());
        assertEquals("title", actualPageable.getSort().getOrderFor("title").getProperty());

        assertEquals(
                Sort.Direction.ASC,
                actualPageable.getSort().getOrderFor("title").getDirection()
        );
    }

    @Test
    public void getPages_ThrowsException_WhenPageIsNegative () {
        assertThrows(
                IllegalArgumentException.class,
                () -> noteService.getNotes(-1, 2, "title", "asc")
        );
    }

    @Test
    public void getPages_ThrowsException_WhenPageSizeIsNotPositive () {
        assertThrows(
                IllegalArgumentException.class,
                () -> noteService.getNotes(0, 0, "title", "asc")
        );
    }

    @Test
    public void getPages_ThrowsException_WhenTitleIsInvalid () {
        assertThrows(
                IllegalArgumentException.class,
                () -> noteService.getNotes(1, 2, "Apache", "asc")
        );
    }

    @Test
    public void getPages_ThrowsException_WhenDirectionIsInvalid () {
        assertThrows(
                IllegalArgumentException.class,
                () -> noteService.getNotes(1, 2, "title", "sideways")
        );
    }
}
