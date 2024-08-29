package com.vhashiro.quizapp.controller;
import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.service.QuestionService;
import com.vhashiro.quizapp.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> startGetQuestionsList(){
        return questionService.getQuestionsList();
    }



}
