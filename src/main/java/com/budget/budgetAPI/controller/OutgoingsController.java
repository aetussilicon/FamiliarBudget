package com.budget.budgetAPI.controller;

import com.budget.budgetAPI.functions.VerifyIfCommentIsDuplicated;
import com.budget.budgetAPI.outgoing.DataOutgoings;
import com.budget.budgetAPI.outgoing.Outgoing;
import com.budget.budgetAPI.outgoing.OutgoingsRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("outgoings")
public class OutgoingsController {

    private final OutgoingsRepository outgoingsRepository;
    private final VerifyIfCommentIsDuplicated verifyIfCommentIsDuplicated;


    public OutgoingsController(OutgoingsRepository outgoingsRepository, VerifyIfCommentIsDuplicated verifyIfCommentIsDuplicated) {
        this.outgoingsRepository = outgoingsRepository;
        this.verifyIfCommentIsDuplicated = verifyIfCommentIsDuplicated;
    }

    @PostMapping
    public void registerOutgoings(@RequestBody @Valid DataOutgoings data) {
        String comments = data.comments();
        String date = data.date();

        if (verifyIfCommentIsDuplicated.verifyIfIsDuplicateOutgoing(comments, date) == true) {
            throw new IllegalArgumentException("Comentário duplicado");
        }
        outgoingsRepository.save(new Outgoing(data));
    }

}
