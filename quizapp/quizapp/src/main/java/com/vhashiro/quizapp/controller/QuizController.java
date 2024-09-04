package com.vhashiro.quizapp.controller;

import com.vhashiro.quizapp.entity.QuestionWrapper;
import com.vhashiro.quizapp.entity.UserResponse;
import com.vhashiro.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    /** Create a Quiz from Questions Table in DB
    quiz/create?questionCategory=java&numQuestions=1&title=FirstQ */
    @PostMapping("create")
    public ResponseEntity<String> startCreateQuiz(@RequestParam String questionCategory,
                                          @RequestParam int numQuestions,
                                          @RequestParam String title){

        return quizService.createQuiz(questionCategory,numQuestions,title);
    }


    /** Get a Wrapped Quiz Object from the Quiz Table in DB
        -rightAnswer -difficultyLevel -questionCategory */
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> startGetQuiz(@PathVariable Integer id){

        return quizService.getQuiz(id);
    }


    /** Check UserResponse to Quiz rightAnswer and get a score */
    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable Integer id,
                                             @RequestBody List<UserResponse> responseList){

        return quizService.calcResult(id, responseList);
    }

}
