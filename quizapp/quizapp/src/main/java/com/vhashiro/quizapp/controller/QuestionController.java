package com.vhashiro.quizapp.controller;
import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.service.QuestionService;
import com.vhashiro.quizapp.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /** Returns Question List + HttpStatus Code */
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> startGetQuestionsList(){
        return questionService.getQuestionsList();
    }

    /** Returns Question List + HttpStatus Code */
    @GetMapping("category/{categoryName}")
    public ResponseEntity<List<Question>> startGetQuestionsListCategory(@PathVariable String categoryName){
        return questionService.getQuestionsListCategory(categoryName);
    }

    @PostMapping("add")
    public ResponseEntity<String> startAddQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }




}
