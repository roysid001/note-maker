package com.sid.notemaker.dto;

import java.time.LocalDateTime;

public class NoteResponseDTO {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponseDTO user;

    public NoteResponseDTO(Integer id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, UserResponseDTO user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UserResponseDTO getUser() {
        return user;
    }
}
