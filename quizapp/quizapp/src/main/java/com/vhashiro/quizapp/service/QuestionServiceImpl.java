package com.vhashiro.quizapp.service;

import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getQuestionsList() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionsListCategory(String categoryName) {
        return questionRepository.findByQuestionCategory(categoryName);
    }

    @Override
    public String addQuestion(Question question) {
        questionRepository.save(question);
        return "question added successfully";
    }

}
