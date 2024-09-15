package com.vhashiro.quizapp.service;

import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = QuestionServiceImpl.class)
class QuestionServiceImplTest {

    @Autowired
    private QuestionServiceImpl questionService;

    @MockBean
    private QuestionRepository questionRepository;


    @Test
    void getQuestionsList() {
        // Mocking the repository's [return value]
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming"),
                new Question(2, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Framework")
        );

        // when [questionRepository] return [mock return value]
        Mockito.when(questionRepository.findAll()).thenReturn(mockQuestions);

        // call [service method] to start TEST
        ResponseEntity<List<Question>> actualResult = questionService.getQuestionsList();



        /** Verify Results **/
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        assertEquals(2, actualResult.getBody().size());
        assertEquals(mockQuestions, actualResult.getBody());
    }

    @Test
    void getQuestionsListCategory() {
        // Mocking the repository's [return value]
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming"),
                new Question(2, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Programming")
        );

        // when [questionRepository] return [mock return value]
        Mockito.when(questionRepository.findByQuestionCategory("Programming"))
                .thenReturn(mockQuestions);

        // call [service method] to start TEST
        ResponseEntity<List<Question>> actualResult = questionService.getQuestionsListCategory("Programming");



        /** Verify Results **/
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        assertEquals(mockQuestions, actualResult.getBody());
    }

    @Test
    void addQuestion() {
        // Mocking the repository's [return value]
        Question question = new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming");

        // when [questionRepository] return [mock return value]
        Mockito.when(questionRepository.save(Mockito.any(Question.class)))
                .thenReturn(new Question());

        // call [service method] to start TEST
        ResponseEntity<String> actualResult = questionService.addQuestion(question);



        /** Verify Results **/
        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
    }
}