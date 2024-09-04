package com.vhashiro.quizapp.service;

import com.vhashiro.quizapp.entity.QuestionWrapper;
import com.vhashiro.quizapp.entity.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    ResponseEntity<String> createQuiz(String questionCategory,int numQuestions, String title);

    ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id);

    ResponseEntity<String> calcResult(Integer id, List<UserResponse> responseList);
}
