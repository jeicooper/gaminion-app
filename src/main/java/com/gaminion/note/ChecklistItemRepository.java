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

    @Query("SELECT COUNT(c) FROM ChecklistItem c WHERE c.notebook.game.id = :gameId AND c.notebook.user.id = :userId")
    int countByGameIdAndUserId(@Param("gameId") Long gameId, @Param("userId") Long userId);

    @Query("SELECT COUNT(c) FROM ChecklistItem c WHERE c.notebook.game.id = :gameId AND c.notebook.user.id = :userId AND c.checked = true")
    int countCompletedByGameIdAndUserId(@Param("gameId") Long gameId, @Param("userId") Long userId);
}