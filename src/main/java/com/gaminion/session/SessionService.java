package com.gaminion.session;

import com.gaminion.game.Game;
import com.gaminion.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private GameRepository gameRepository;

    public SessionResponseDTO startSession(SessionStartDTO dto) {
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + dto.getGameId()));

        sessionRepository.findByGameIdAndStatus(dto.getGameId(), SessionStatus.ACTIVE)
                .ifPresent(s -> {
                    throw new RuntimeException("A session is already active for: " + game.getName());
                });

        Session session = new Session();
        session.setGame(game);
        session.setNotes(dto.getNotes());

        return SessionResponseDTO.from(sessionRepository.save(session));
    }

    public SessionResponseDTO stopSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + sessionId));

        if (session.getStatus() == SessionStatus.COMPLETED) {
            throw new RuntimeException("Session is already completed");
        }

        session.stopSession();
        return SessionResponseDTO.from(sessionRepository.save(session));
    }

    public List<SessionResponseDTO> getSessionsByGame(Long gameId) {
        return sessionRepository.findByGameId(gameId)
                .stream()
                .map(SessionResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<SessionResponseDTO> getActiveSessions() {
        return sessionRepository.findByStatus(SessionStatus.ACTIVE)
                .stream()
                .map(SessionResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<SessionResponseDTO> getAllSessions() {
        return sessionRepository.findAll()
                .stream()
                .map(SessionResponseDTO::from)
                .collect(Collectors.toList());
    }
}