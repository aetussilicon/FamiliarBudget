package com.budget.budgetAPI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloControler {

    public String hello(){
        return "Welcome";
    }

}
