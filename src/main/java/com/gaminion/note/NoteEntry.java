package com.gaminion.note;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "note_entries")
public class NoteEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notebook_id", nullable = false)
    private Notebook notebook;

    @Enumerated(EnumType.STRING)
    private EntryType type;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean isPinned = false;
    private LocalDateTime createdAt;

    private LocalDateTime reminderDateTime;
    private Boolean reminderSent = false;

    @OneToMany(mappedBy = "noteEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChecklistItem> checklistItems = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Notebook getNotebook() { return notebook; }
    public void setNotebook(Notebook notebook) { this.notebook = notebook; }

    public EntryType getType() { return type; }
    public void setType(EntryType type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Boolean getIsPinned() { return isPinned; }
    public void setIsPinned(Boolean isPinned) { this.isPinned = isPinned; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getReminderDateTime() { return reminderDateTime; }
    public void setReminderDateTime(LocalDateTime reminderDateTime) { this.reminderDateTime = reminderDateTime; }

    public Boolean getReminderSent() { return reminderSent; }
    public void setReminderSent(Boolean reminderSent) { this.reminderSent = reminderSent; }

    public List<ChecklistItem> getChecklistItems() { return checklistItems; }
    public void setChecklistItems(List<ChecklistItem> checklistItems) { this.checklistItems = checklistItems; }
}