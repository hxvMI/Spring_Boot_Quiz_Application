package com.vhashiro.quizapp.service;
import com.vhashiro.quizapp.entity.Question;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface QuestionService {

    ResponseEntity<List<Question>> getQuestionsList();

    ResponseEntity<List<Question>> getQuestionsListCategory(String categoryName);

    ResponseEntity<String> addQuestion(Question question);
}
