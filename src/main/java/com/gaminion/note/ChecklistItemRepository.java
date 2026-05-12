package com.gaminion.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long> {
    List<ChecklistItem> findByNoteEntryIdOrderByDisplayOrderAsc(Long noteEntryId);
    List<ChecklistItem> findByNoteEntryIdAndIsCheckedFalse(Long noteEntryId);

    @Query("SELECT COUNT(c) FROM ChecklistItem c WHERE c.noteEntry.notebook.game.id = :gameId")
    int countByGameId(@Param("gameId") Long gameId);

    @Query("SELECT COUNT(c) FROM ChecklistItem c WHERE c.noteEntry.notebook.game.id = :gameId AND c.isChecked = true")
    int countCompletedByGameId(@Param("gameId") Long gameId);
}