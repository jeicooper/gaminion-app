package com.gaminion.summary;

import com.gaminion.auth.User;
import com.gaminion.auth.UserRepository;
import com.gaminion.game.Game;
import com.gaminion.game.GameRepository;
import com.gaminion.note.ChecklistItemRepository;
import com.gaminion.note.NoteEntryRepository;
import com.gaminion.session.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SummaryCardService {

    private final GameRepository gameRepository;
    private final SessionRepository sessionRepository;
    private final NoteEntryRepository noteEntryRepository;
    private final ChecklistItemRepository checklistItemRepository;
    private final UserRepository userRepository;

    public SummaryCardDTO generateSummary(Long gameId, String username) {  // changed Long userId → String username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Long userId = user.getId();  // resolve userId here

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        long totalMinutes = sessionRepository.sumDurationByGameAndUser(gameId, userId);
        int totalNotes = noteEntryRepository.countByGameIdAndUserId(gameId, userId);
        int totalChecklists = checklistItemRepository.countByGameIdAndUserId(gameId, userId);
        int completedChecklists = checklistItemRepository.countCompletedByGameIdAndUserId(gameId, userId);
        double completionPct = totalChecklists > 0
                ? Math.round((completedChecklists * 100.0 / totalChecklists) * 10.0) / 10.0
                : 0.0;

        LocalDate firstSession = sessionRepository.findEarliestSessionDate(gameId, userId);
        LocalDate lastSession = sessionRepository.findLatestSessionDate(gameId, userId);

        return SummaryCardDTO.builder()
                .gameId(gameId)
                .gameName(game.getName())
                .totalPlaytimeMinutes(totalMinutes)
                .totalNotes(totalNotes)
                .totalChecklists(totalChecklists)
                .completedChecklists(completedChecklists)
                .checklistCompletionPercent(completionPct)
                .firstSession(firstSession)
                .lastSession(lastSession)
                .build();
    }
}