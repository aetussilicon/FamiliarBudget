package com.budget.budgetAPI.outgoing;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateOutgoingData(
        @NotNull
        Long id,
        String member,
        String comments,
        BigDecimal price,
        String date) {
}
