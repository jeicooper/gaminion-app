package com.gaminion.session;

import com.gaminion.game.Game;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.Duration;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    private String notes;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long durationSeconds;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    @PrePersist
    protected void onCreate() {
        startTime = LocalDateTime.now();
        status = SessionStatus.ACTIVE;
    }

    public void stopSession() {
        this.endTime = LocalDateTime.now();
        this.status = SessionStatus.COMPLETED;
        this.durationSeconds = Duration.between(startTime, endTime).getSeconds();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Long getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Long durationSeconds) { this.durationSeconds = durationSeconds; }

    public SessionStatus getStatus() { return status; }
    public void setStatus(SessionStatus status) { this.status = status; }
}