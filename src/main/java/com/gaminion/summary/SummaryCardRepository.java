package com.gaminion.summary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SummaryCardRepository extends JpaRepository<SummaryCard, Long> {
    Optional<SummaryCard> findBySessionId(Long sessionId);
    List<SummaryCard> findBySessionGameId(Long gameId);
}