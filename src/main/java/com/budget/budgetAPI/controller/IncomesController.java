package com.budget.budgetAPI.controller;

import com.budget.budgetAPI.income.DataIncomes;
import com.budget.budgetAPI.income.Income;
import com.budget.budgetAPI.income.IncomeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("incomes")
public class IncomesController {

    @Autowired
    private IncomeRepository repository;

    @PostMapping
    public void registerIncomes(@RequestBody @Valid DataIncomes data){
        repository.save(new Income(data));

    }

}
