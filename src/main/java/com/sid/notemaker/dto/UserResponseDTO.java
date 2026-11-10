package com.sid.notemaker.dto;

public class UserResponseDTO {
    private Integer id;
    private String displayName;
    private String email;

    public UserResponseDTO(Integer id, String displayName, String email) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }
}
