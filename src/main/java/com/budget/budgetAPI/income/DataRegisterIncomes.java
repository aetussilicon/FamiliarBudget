package com.budget.budgetAPI.income;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

//DTO para registro de receitas.
public record DataRegisterIncomes(

        @NotBlank
        String member,

        @NotBlank
        String date,

        @NotNull
        @DecimalMin(value = "0.00", inclusive = false)
        @Digits(integer = 3, fraction = 2)
        BigDecimal price,

        @NotEmpty
        String comments
) {
}
