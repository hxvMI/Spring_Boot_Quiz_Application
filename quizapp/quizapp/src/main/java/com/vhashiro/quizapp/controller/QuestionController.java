package com.vhashiro.quizapp.controller;
import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.service.QuestionService;
import com.vhashiro.quizapp.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("category/{categoryName}")
    public List<Question> startGetQuestionsListCategory(@PathVariable String categoryName){
        return questionService.getQuestionsListCategory(categoryName);
    }

    @PostMapping("add")
    public String startAddQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }




}
