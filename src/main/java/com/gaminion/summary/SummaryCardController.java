package com.gaminion.summary;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summary")
@RequiredArgsConstructor
public class SummaryCardController {

    private final SummaryCardService summaryCardService;

    @GetMapping("/{gameId}")
    public ResponseEntity<SummaryCardDTO> getSummaryCard(
            @PathVariable Long gameId,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        return ResponseEntity.ok(summaryCardService.generateSummary(gameId, username));
    }
}