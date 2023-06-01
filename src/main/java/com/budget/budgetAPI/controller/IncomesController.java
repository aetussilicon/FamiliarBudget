package com.budget.budgetAPI.controller;

import com.budget.budgetAPI.functions.VerifyIfCommentIsDuplicated;
import com.budget.budgetAPI.income.DataIncomes;
import com.budget.budgetAPI.income.Income;
import com.budget.budgetAPI.income.IncomeRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("incomes")
public class IncomesController {

    private final IncomeRepository repository;
    private final VerifyIfCommentIsDuplicated verifyIfCommentIsDuplicated;

    public IncomesController(IncomeRepository repository, VerifyIfCommentIsDuplicated verifyIfCommentIsDuplicated) {
        this.repository = repository;
        this.verifyIfCommentIsDuplicated = verifyIfCommentIsDuplicated;
    }

    @PostMapping
    public void registerIncomes(@RequestBody @Valid DataIncomes data) {
        String comments = data.comments();
        String date = data.date();

        if (verifyIfCommentIsDuplicated.verifyIfIsDuplicateIncome(comments, date) == true) {
            throw new IllegalArgumentException("Comentário duplicado");
        }  repository.save(new Income(data));
    }
}