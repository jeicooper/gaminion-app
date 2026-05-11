package com.gaminion.note;

import com.gaminion.game.Game;
import com.gaminion.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private NoteEntryRepository noteEntryRepository;

    @Autowired
    private ChecklistItemRepository checklistItemRepository;

    @Autowired
    private GameRepository gameRepository;

    public Notebook createNotebook(NotebookDTO dto) {
        Game game = gameRepository.findById(dto.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));
        Notebook notebook = new Notebook();
        notebook.setGame(game);
        notebook.setTitle(dto.getTitle());
        return notebookRepository.save(notebook);
    }

    public List<Notebook> getNotebooksByGame(Long gameId) {
        return notebookRepository.findByGameId(gameId);
    }

    public NoteEntry addEntry(Long notebookId, NoteEntryDTO dto) {
        Notebook notebook = notebookRepository.findById(notebookId)
                .orElseThrow(() -> new RuntimeException("Notebook not found"));
        NoteEntry entry = new NoteEntry();
        entry.setNotebook(notebook);
        entry.setType(dto.getType());
        entry.setTitle(dto.getTitle());
        entry.setContent(dto.getContent());
        entry.setIsPinned(dto.getIsPinned());
        if (dto.getType() == EntryType.REMINDER && dto.getReminderDateTime() != null) {
            entry.setReminderDateTime(dto.getReminderDateTime());
        }
        return noteEntryRepository.save(entry);
    }

    public List<NoteEntry> getEntriesByNotebook(Long notebookId) {
        return noteEntryRepository.findByNotebookId(notebookId);
    }

    public List<NoteEntry> getEntriesByType(Long notebookId, EntryType type) {
        return noteEntryRepository.findByNotebookIdAndType(notebookId, type);
    }

    public List<NoteEntry> getPinnedEntries(Long notebookId) {
        return noteEntryRepository.findByNotebookIdAndIsPinnedTrue(notebookId);
    }

    public NoteEntry togglePin(Long entryId) {
        NoteEntry entry = noteEntryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        entry.setIsPinned(!entry.getIsPinned());
        return noteEntryRepository.save(entry);
    }

    public void deleteEntry(Long entryId) {
        noteEntryRepository.deleteById(entryId);
    }

    public ChecklistItem addChecklistItem(Long entryId, ChecklistItemDTO dto) {
        NoteEntry entry = noteEntryRepository.findById(entryId)
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        ChecklistItem item = new ChecklistItem();
        item.setNoteEntry(entry);
        item.setContent(dto.getContent());
        item.setIsPinned(dto.getIsPinned());
        item.setDisplayOrder(dto.getDisplayOrder());
        return checklistItemRepository.save(item);
    }

    public ChecklistItem toggleChecklistItem(Long itemId) {
        ChecklistItem item = checklistItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setIsChecked(!item.getIsChecked());
        return checklistItemRepository.save(item);
    }

    public List<ChecklistItem> getChecklistItems(Long entryId) {
        return checklistItemRepository.findByNoteEntryIdOrderByDisplayOrderAsc(entryId);
    }

    public void deleteChecklistItem(Long itemId) {
        checklistItemRepository.deleteById(itemId);
    }
}