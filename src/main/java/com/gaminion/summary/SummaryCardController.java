package com.gaminion.summary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/summary")
public class SummaryCardController {

    @Autowired
    private SummaryCardService summaryCardService;

    @Value("${gaminion.storage.path}")
    private String storagePath;

    @PostMapping("/generate/{sessionId}")
    public ResponseEntity<SummaryCard> generateCard(@PathVariable Long sessionId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(summaryCardService.generateCard(sessionId));
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<SummaryCard> getCardBySession(@PathVariable Long sessionId) {
        return ResponseEntity.ok(summaryCardService.getCardBySession(sessionId));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<SummaryCard>> getCardsByGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(summaryCardService.getCardsByGame(gameId));
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadCard(@PathVariable String fileName) {
        File file = new File(storagePath + "/" + fileName);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}