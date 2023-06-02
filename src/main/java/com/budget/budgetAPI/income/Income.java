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
}
