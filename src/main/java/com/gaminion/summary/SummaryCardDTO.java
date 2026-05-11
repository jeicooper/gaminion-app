package com.gaminion.summary;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class SummaryCardDTO {
    private Long gameId;
    private String gameName;
    private long totalPlaytimeMinutes;
    private int totalNotes;
    private int totalChecklists;
    private int completedChecklists;
    private double checklistCompletionPercent;
    private LocalDate firstSession;
    private LocalDate lastSession;
}