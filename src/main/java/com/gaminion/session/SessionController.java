package com.gaminion.session;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/start")
    public ResponseEntity<SessionResponseDTO> startSession(@Valid @RequestBody SessionStartDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.startSession(dto));
    }

    @PatchMapping("/{id}/stop")
    public ResponseEntity<SessionResponseDTO> stopSession(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.stopSession(id));
    }

    @GetMapping
    public ResponseEntity<List<SessionResponseDTO>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @GetMapping("/active")
    public ResponseEntity<List<SessionResponseDTO>> getActiveSessions() {
        return ResponseEntity.ok(sessionService.getActiveSessions());
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<SessionResponseDTO>> getSessionsByGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(sessionService.getSessionsByGame(gameId));
    }
}