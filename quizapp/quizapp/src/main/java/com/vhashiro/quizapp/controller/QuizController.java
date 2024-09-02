package com.vhashiro.quizapp.controller;

import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.service.QuestionService;
import com.vhashiro.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    /** quiz/create?questionCategory=java&numQuestions=1&title=FirstQ */
    @PostMapping("create")
    public ResponseEntity<String> startCreateQuiz(@RequestParam String questionCategory,
                                          @RequestParam int numQuestions,
                                          @RequestParam String title){

        return quizService.createQuiz(questionCategory,numQuestions,title);
    }

}
