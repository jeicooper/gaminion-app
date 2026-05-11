package com.gaminion.note;

import jakarta.validation.constraints.NotBlank;

public class ChecklistItemDTO {

    @NotBlank(message = "Content is required")
    private String content;

    private Boolean isPinned = false;
    private Integer displayOrder = 0;

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Boolean getIsPinned() { return isPinned; }
    public void setIsPinned(Boolean isPinned) { this.isPinned = isPinned; }

    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
}