package com.budget.budgetAPI.controller;

import com.budget.budgetAPI.functions.VerifyIfCommentIsDuplicated;
import com.budget.budgetAPI.income.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("incomes")
public class IncomesController {
    @Autowired
    private IncomeRepository repository;
    private final VerifyIfCommentIsDuplicated verifyIfCommentIsDuplicated;

    Income income;
    String comments;
    String date;

    public IncomesController(IncomeRepository repository, VerifyIfCommentIsDuplicated verifyIfCommentIsDuplicated) {
        //this.repository = repository;
        this.verifyIfCommentIsDuplicated = verifyIfCommentIsDuplicated;
    }

    //Método para registro de receitas.
    @PostMapping
    public void registerIncomes(@RequestBody @Valid DataRegisterIncomes data) {

        /*Método que verifica se um comentário está duplicado em um mesmmo mês,
        dentro do database, se estiver, lança uma exception, se não, registra.
        */
        if (verifyIfCommentIsDuplicated.verifyIfIsDuplicateIncome(comments, date) == true) {
            throw new IllegalArgumentException("Comentário duplicado");
        }
        repository.save(new Income(data));
    }

    //Método Get para paginação de receitas.
    //5 registros por página e ordenados por data.
    @GetMapping
    public Page<DataListIncomes> listIncomes(@PageableDefault(size = 5, sort = {"date"}) Pageable paginationIncomes) {
        return repository.findAll(paginationIncomes).map(DataListIncomes::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        Optional<Income> income = repository.findById(id);
        if (income.isPresent()) {
            return ResponseEntity.ok(income.get());
        }
        return ResponseEntity.notFound().build();
    }


    //Atualiza uma informção dentro do banco de dados das receitas
    @PutMapping
    @Transactional
    public void updateIncomes(@RequestBody @Valid UpdateIncomeData data) {
        Long id = data.id();
        Optional<Income> optionalIncome = Optional.of(repository.getReferenceById(id));
        if (optionalIncome.isPresent()) {
            income = optionalIncome.get();
            {
                comments = data.comments();
                date = data.date();

                if (verifyIfCommentIsDuplicated.verifyIfIsDuplicateIncome(comments, date) &&
                        !comments.equals(income.getComments())) {
                    throw new IllegalArgumentException("Comentário duplicado");
                }
                income.updateData(data);
            }
        }
    }
}