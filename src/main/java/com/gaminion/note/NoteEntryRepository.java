package com.gaminion.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface NoteEntryRepository extends JpaRepository<NoteEntry, Long> {
    List<NoteEntry> findByNotebookId(Long notebookId);
    List<NoteEntry> findByNotebookIdAndType(Long notebookId, EntryType type);
    List<NoteEntry> findByNotebookIdAndIsPinnedTrue(Long notebookId);
    List<NoteEntry> findByTypeAndReminderDateTimeBeforeAndReminderSentFalse(
            EntryType type, LocalDateTime dateTime
    );

    @Query("SELECT COUNT(n) FROM NoteEntry n WHERE n.notebook.game.id = :gameId")
    int countByGameId(@Param("gameId") Long gameId);
}