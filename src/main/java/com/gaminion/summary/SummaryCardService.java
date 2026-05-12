package com.gaminion.summary;

import com.gaminion.session.Session;
import com.gaminion.session.SessionRepository;
import com.gaminion.session.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;

@Service
public class SummaryCardService {

    @Autowired
    private SummaryCardRepository summaryCardRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CardImageGenerator cardImageGenerator;

    @Value("${gaminion.storage.path}")
    private String storagePath;

    public SummaryCard generateCard(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (session.getStatus() != SessionStatus.COMPLETED) {
            throw new RuntimeException("Cannot generate card for an active session. Stop the session first.");
        }

        summaryCardRepository.findBySessionId(sessionId).ifPresent(existing -> {
            throw new RuntimeException("Summary card already exists for this session");
        });

        try {
            File cardFile = cardImageGenerator.generateCard(session, storagePath);

            SummaryCard card = new SummaryCard();
            card.setSession(session);
            card.setImagePath(cardFile.getAbsolutePath());
            card.setShareableFileName(cardFile.getName());

            return summaryCardRepository.save(card);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate summary card: " + e.getMessage());
        }
    }

    public SummaryCard getCardBySession(Long sessionId) {
        return summaryCardRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new RuntimeException("No summary card found for this session"));
    }

    public List<SummaryCard> getCardsByGame(Long gameId) {
        return summaryCardRepository.findBySessionGameId(gameId);
    }
}