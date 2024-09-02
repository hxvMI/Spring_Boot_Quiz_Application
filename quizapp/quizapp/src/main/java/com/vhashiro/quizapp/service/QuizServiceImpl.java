package com.vhashiro.quizapp.service;

import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.entity.Quiz;
import com.vhashiro.quizapp.repository.QuestionRepository;
import com.vhashiro.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    /** Get Questions List from DataBase
     *  Store them in Quiz object
     *  Save new Quiz object to Quiz DataBase */
    @Override
    public ResponseEntity<String> createQuiz(String questionCategory, int numQuestions, String title) {

        List<Question> questionList = questionRepository.
                findRandomQuestionsByQuestionCategory(questionCategory, numQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsList(questionList);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Created new Quiz", HttpStatus.CREATED);
    }


}
