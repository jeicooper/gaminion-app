package com.gaminion.note;

import jakarta.persistence.*;

@Entity
@Table(name = "checklist_items")
public class ChecklistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "note_entry_id", nullable = false)
    private NoteEntry noteEntry;

    private String content;
    private Boolean isChecked = false;
    private Boolean isPinned = false;
    private Integer displayOrder = 0;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public NoteEntry getNoteEntry() { return noteEntry; }
    public void setNoteEntry(NoteEntry noteEntry) { this.noteEntry = noteEntry; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Boolean getIsChecked() { return isChecked; }
    public void setIsChecked(Boolean isChecked) { this.isChecked = isChecked; }

    public Boolean getIsPinned() { return isPinned; }
    public void setIsPinned(Boolean isPinned) { this.isPinned = isPinned; }

    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
}