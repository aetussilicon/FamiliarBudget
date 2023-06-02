package com.budget.budgetAPI.income;

import java.math.BigDecimal;

//DTO para listagem de receitas.
public record DataListIncomes(
        String member,
        String comments,
        BigDecimal price,
        String date) {

    public DataListIncomes(Income income){
        this(income.getMember(), income.getComments(), income.getPrice(), income.getDate());
    }
}
