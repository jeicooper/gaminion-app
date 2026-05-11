package com.gaminion.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoteEntryRepository extends JpaRepository<NoteEntry, Long> {
    List<NoteEntry> findByNotebookId(Long notebookId);
    List<NoteEntry> findByNotebookIdAndType(Long notebookId, EntryType type);
    List<NoteEntry> findByNotebookIdAndIsPinnedTrue(Long notebookId);
    List<NoteEntry> findByTypeAndReminderDateTimeBeforeAndReminderSentFalse(
            EntryType type, LocalDateTime dateTime
    );
}