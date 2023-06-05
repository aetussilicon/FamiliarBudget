package com.budget.budgetAPI.functions;

import com.budget.budgetAPI.income.Income;
import com.budget.budgetAPI.income.IncomeRepository;
import com.budget.budgetAPI.outgoing.Outgoing;
import com.budget.budgetAPI.outgoing.OutgoingsRepository;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

//Métodos para verificar se um comentário está duplicado.
@Component
public class VerifyIfCommentIsDuplicated {

    @Autowired
    private IncomeRepository repository;
    @Autowired
    private OutgoingsRepository outgoingsRepository;

    public <T> boolean verifyIfIsDuplicate(String comments, String date, CrudRepository<T, Long> repository){
        Optional<T> entity = repository.findByCommentAndDate(comments, date);
        return entity.isPresent();
    }

    public boolean verifyIfIsDuplicateIncome(String comments, String date) {
        Optional<Income> income = repository.findByCommentAndDate(comments, date);
        return income.isPresent();
    }
    public boolean verifyIfIsDuplicateOutgoing(String comments, String date) {
        Optional<Outgoing> outgoing = outgoingsRepository.findByCommentAndDate(comments, date);
        return outgoing.isPresent();
    }

}
