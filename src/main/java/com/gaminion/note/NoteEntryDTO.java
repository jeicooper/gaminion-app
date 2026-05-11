package com.gaminion.note;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class NoteEntryDTO {

    @NotNull(message = "Entry type is required")
    private EntryType type;

    private String title;
    private String content;
    private Boolean isPinned = false;
    private LocalDateTime reminderDateTime;

    public EntryType getType() { return type; }
    public void setType(EntryType type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Boolean getIsPinned() { return isPinned; }
    public void setIsPinned(Boolean isPinned) { this.isPinned = isPinned; }

    public LocalDateTime getReminderDateTime() { return reminderDateTime; }
    public void setReminderDateTime(LocalDateTime reminderDateTime) { this.reminderDateTime = reminderDateTime; }
}