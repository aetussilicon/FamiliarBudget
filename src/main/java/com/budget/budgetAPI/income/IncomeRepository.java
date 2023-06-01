package com.budget.budgetAPI.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    @Query("SELECT i FROM income i WHERE i.comments = :comment AND i.date = :date")
    Optional<Income> findByCommentAndDate(@Param("comment") String comment, @Param("date") String date);
}
