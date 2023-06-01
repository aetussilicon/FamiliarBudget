package com.budget.budgetAPI.outgoing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OutgoingsRepository extends JpaRepository<Outgoing, Long> {
    @Query("SELECT i FROM outgoing i WHERE i.comments = :comment AND i.date = :date")
    Optional<Outgoing> findByCommentAndDate(@Param("comment") String comment, @Param("date") String date);
}
