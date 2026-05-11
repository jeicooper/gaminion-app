package com.gaminion.session;

import java.time.LocalDateTime;

public class SessionResponseDTO {

    private Long id;
    private String gameName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long durationSeconds;
    private String durationFormatted;
    private SessionStatus status;
    private String notes;

    public static SessionResponseDTO from(Session session) {
        SessionResponseDTO dto = new SessionResponseDTO();
        dto.id = session.getId();
        dto.gameName = session.getGame().getName();
        dto.startTime = session.getStartTime();
        dto.endTime = session.getEndTime();
        dto.durationSeconds = session.getDurationSeconds();
        dto.status = session.getStatus();
        dto.notes = session.getNotes();

        if (session.getDurationSeconds() != null) {
            long hours = session.getDurationSeconds() / 3600;
            long minutes = (session.getDurationSeconds() % 3600) / 60;
            long seconds = session.getDurationSeconds() % 60;
            dto.durationFormatted = String.format("%dh %dm %ds", hours, minutes, seconds);
        }

        return dto;
    }

    public Long getId() { return id; }
    public String getGameName() { return gameName; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public Long getDurationSeconds() { return durationSeconds; }
    public String getDurationFormatted() { return durationFormatted; }
    public SessionStatus getStatus() { return status; }
    public String getNotes() { return notes; }
}
