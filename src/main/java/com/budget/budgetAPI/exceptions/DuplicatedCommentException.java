package com.budget.budgetAPI.exceptions;

public class DuplicatedCommentException extends Throwable {

    private static final String msg = "Comentário duplicado";
    public DuplicatedCommentException(){
        super(msg);
    }
}
