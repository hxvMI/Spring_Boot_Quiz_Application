package com.vhashiro.quizapp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

public interface QuizService {

    ResponseEntity<String> createQuiz(String questionCategory,int numQuestions, String title);

}
