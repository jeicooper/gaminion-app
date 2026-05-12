package com.gaminion.summary;

import com.gaminion.session.Session;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "summary_cards")
public class SummaryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    private String imagePath;
    private String shareableFileName;
    private LocalDateTime generatedAt;

    @PrePersist
    protected void onCreate() {
        generatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Session getSession() { return session; }
    public void setSession(Session session) { this.session = session; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getShareableFileName() { return shareableFileName; }
    public void setShareableFileName(String shareableFileName) { this.shareableFileName = shareableFileName; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}
