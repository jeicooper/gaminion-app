package com.gaminion.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long> {
    List<ChecklistItem> findByNoteEntryIdOrderByDisplayOrderAsc(Long noteEntryId);
    List<ChecklistItem> findByNoteEntryIdAndIsCheckedFalse(Long noteEntryId);
}