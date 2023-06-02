package com.budget.budgetAPI.controller;

import com.budget.budgetAPI.functions.VerifyIfCommentIsDuplicated;
import com.budget.budgetAPI.outgoing.DataListOutgoings;
import com.budget.budgetAPI.outgoing.DataOutgoings;
import com.budget.budgetAPI.outgoing.Outgoing;
import com.budget.budgetAPI.outgoing.OutgoingsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("outgoings")
public class OutgoingsController {
    @Autowired
    private OutgoingsRepository outgoingsRepository;
    private final VerifyIfCommentIsDuplicated verifyIfCommentIsDuplicated;

    public OutgoingsController(OutgoingsRepository outgoingsRepository, VerifyIfCommentIsDuplicated verifyIfCommentIsDuplicated) {
      //  this.outgoingsRepository = outgoingsRepository;
        this.verifyIfCommentIsDuplicated = verifyIfCommentIsDuplicated;
    }

    //Método post para cadastro de despesas.
    @PostMapping
    public void registerOutgoings(@RequestBody @Valid DataOutgoings data) {
        String comments = data.comments();
        String date = data.date();

        /*Método que verifica se um comentário está duplicado em um mesmmo mês,
        dentro do database, se estiver, lança uma exception, se não, registra.
        */
        if (verifyIfCommentIsDuplicated.verifyIfIsDuplicateOutgoing(comments, date) == true) {
            throw new IllegalArgumentException("Comentário duplicado");
        }
        outgoingsRepository.save(new Outgoing(data));
    }

    //Método Get para paginação de despesas.
    //5 registros por página e ordenados por data.
    @GetMapping
    public Page<DataListOutgoings> listOutgoings(@PageableDefault(size = 5, sort = {"date"})Pageable paginationOutgoings){
        return outgoingsRepository.findAll(paginationOutgoings).map(DataListOutgoings::new);
    }

}
