package com.gaminion.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByGameId(Long gameId);
    Optional<Session> findByGameIdAndStatus(Long gameId, SessionStatus status);
    List<Session> findByStatus(SessionStatus status);

    @Query("SELECT COALESCE(SUM(s.durationMinutes), 0) FROM Session s WHERE s.game.id = :gameId AND s.user.id = :userId")
    long sumDurationByGameAndUser(@Param("gameId") Long gameId, @Param("userId") Long userId);

    @Query("SELECT CAST(MIN(s.startTime) AS LocalDate) FROM Session s WHERE s.game.id = :gameId AND s.user.id = :userId")
    LocalDate findEarliestSessionDate(@Param("gameId") Long gameId, @Param("userId") Long userId);

    @Query("SELECT CAST(MAX(s.startTime) AS LocalDate) FROM Session s WHERE s.game.id = :gameId AND s.user.id = :userId")
    LocalDate findLatestSessionDate(@Param("gameId") Long gameId, @Param("userId") Long userId);
}
