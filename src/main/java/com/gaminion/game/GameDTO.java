package com.gaminion.game;

import jakarta.validation.constraints.NotBlank;

public class GameDTO {

    @NotBlank(message = "Game name is required")
    private String name;
    private String genre;
    private String platform;
    private String coverImageUrl;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public String getCoverImageUrl() { return coverImageUrl; }
    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }
}