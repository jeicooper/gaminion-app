package com.gaminion.session;

import jakarta.validation.constraints.NotNull;

public class SessionStartDTO {

    @NotNull(message = "Game ID is required")
    private Long gameId;

    private String notes;

    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}