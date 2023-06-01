package com.budget.budgetAPI.outgoing;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DataOutgoings(

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
