package com.gaminion.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NotebookDTO {

    @NotNull(message = "Game ID is required")
    private Long gameId;

    @NotBlank(message = "Title is required")
    private String title;

    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
