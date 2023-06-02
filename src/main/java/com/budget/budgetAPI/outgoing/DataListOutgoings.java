package com.budget.budgetAPI.outgoing;

import java.math.BigDecimal;

//DTO para listagem de despesas.
public record DataListOutgoings(
        String member,
        String comments,
        BigDecimal price,
        String date) {

    public DataListOutgoings(Outgoing outgoing){
        this(outgoing.getMember(), outgoing.getComments(), outgoing.getPrice(), outgoing.getDate());
    }
}
