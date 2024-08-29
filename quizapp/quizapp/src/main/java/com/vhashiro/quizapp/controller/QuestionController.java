package com.vhashiro.quizapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    /** http://localhost:8083/SetupTest */
    @GetMapping("/SetupTest")
    public String helloWorld() {
        return "hello world";
    }


}
