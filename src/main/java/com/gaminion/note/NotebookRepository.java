package com.gaminion.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long> {
    List<Notebook> findByGameId(Long gameId);
    Optional<Notebook> findByGameIdAndTitle(Long gameId, String title);
}
