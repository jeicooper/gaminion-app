package com.gaminion.note;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/notebooks")
    public ResponseEntity<Notebook> createNotebook(@Valid @RequestBody NotebookDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNotebook(dto));
    }

    @GetMapping("/notebooks/game/{gameId}")
    public ResponseEntity<List<Notebook>> getNotebooksByGame(@PathVariable Long gameId) {
        return ResponseEntity.ok(noteService.getNotebooksByGame(gameId));
    }

    @PostMapping("/notebooks/{notebookId}/entries")
    public ResponseEntity<NoteEntry> addEntry(@PathVariable Long notebookId,
                                              @Valid @RequestBody NoteEntryDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.addEntry(notebookId, dto));
    }

    @GetMapping("/notebooks/{notebookId}/entries")
    public ResponseEntity<List<NoteEntry>> getEntries(@PathVariable Long notebookId) {
        return ResponseEntity.ok(noteService.getEntriesByNotebook(notebookId));
    }

    @GetMapping("/notebooks/{notebookId}/entries/type/{type}")
    public ResponseEntity<List<NoteEntry>> getEntriesByType(@PathVariable Long notebookId,
                                                            @PathVariable EntryType type) {
        return ResponseEntity.ok(noteService.getEntriesByType(notebookId, type));
    }

    @GetMapping("/notebooks/{notebookId}/entries/pinned")
    public ResponseEntity<List<NoteEntry>> getPinnedEntries(@PathVariable Long notebookId) {
        return ResponseEntity.ok(noteService.getPinnedEntries(notebookId));
    }

    @PatchMapping("/entries/{entryId}/pin")
    public ResponseEntity<NoteEntry> togglePin(@PathVariable Long entryId) {
        return ResponseEntity.ok(noteService.togglePin(entryId));
    }

    @DeleteMapping("/entries/{entryId}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long entryId) {
        noteService.deleteEntry(entryId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/entries/{entryId}/checklist")
    public ResponseEntity<ChecklistItem> addChecklistItem(@PathVariable Long entryId,
                                                          @Valid @RequestBody ChecklistItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.addChecklistItem(entryId, dto));
    }

    @PatchMapping("/checklist/{itemId}/toggle")
    public ResponseEntity<ChecklistItem> toggleChecklistItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(noteService.toggleChecklistItem(itemId));
    }

    @GetMapping("/entries/{entryId}/checklist")
    public ResponseEntity<List<ChecklistItem>> getChecklistItems(@PathVariable Long entryId) {
        return ResponseEntity.ok(noteService.getChecklistItems(entryId));
    }

    @DeleteMapping("/checklist/{itemId}")
    public ResponseEntity<Void> deleteChecklistItem(@PathVariable Long itemId) {
        noteService.deleteChecklistItem(itemId);
        return ResponseEntity.noContent().build();
    }
}