package com.sid.notemaker.mapper;

import com.sid.notemaker.dto.NoteResponseDTO;
import com.sid.notemaker.dto.UserResponseDTO;
import com.sid.notemaker.model.Note;
import com.sid.notemaker.model.User;

public class NoteMapper {
    public static UserResponseDTO toUserResponseDTO (User user) {
        return new UserResponseDTO (
                user.getId(),
                user.getDisplayName(),
                user.getEmail()
        );
    }

    public static NoteResponseDTO toNoteResponseDTO (Note note) {
        UserResponseDTO userRespDTO = note.getUser() == null ? null : toUserResponseDTO(note.getUser());
        return new NoteResponseDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt(),
                userRespDTO
        );
    }
}
