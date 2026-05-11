package com.gaminion.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByGameId(Long gameId);
    Optional<Session> findByGameIdAndStatus(Long gameId, SessionStatus status);
    List<Session> findByStatus(SessionStatus status);
}
