package com.budget.budgetAPI.income;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateIncomeData(

        @NotNull
        Long id,
        String member,
        String comments,
        BigDecimal price,
        String date) {
}
