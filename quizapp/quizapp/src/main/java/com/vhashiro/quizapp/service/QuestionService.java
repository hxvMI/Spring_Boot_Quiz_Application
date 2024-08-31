package com.vhashiro.quizapp.service;

import com.vhashiro.quizapp.entity.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getQuestionsList();

    List<Question> getQuestionsListCategory(String categoryName);

    String addQuestion(Question question);
}
