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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String member;
    private String date;
    private BigDecimal price;
    private String comments;


    public Income(DataRegisterIncomes data) {
        this.member = data.member();
        this.date = data.date();
        this.price = data.price();
        this.comments = data.comments();
    }

    public Income(UpdateIncomeData data) {
    }

    public void updateData(UpdateIncomeData data) {
        if (data.member() != null) {
            this.member = data.member();
        }

        if (data.date() != null) {
            this.date = data.date();
        }

        if (data.price() != null) {
            this.price = data.price();
        }

        if (data.comments() != null) {
            this.comments = data.comments();
        }

    }
}
