package com.budget.budgetAPI.income;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "incomes")
@Entity(name = "income")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Income {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private BigDecimal value;
    private String description;

    public Income(DataIncomes data) {
        this.date = data.date();
        this.value = data.value();
        this.description = data.description();
    }
}
