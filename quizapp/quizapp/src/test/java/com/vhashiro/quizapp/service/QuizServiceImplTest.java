package com.vhashiro.quizapp.service;

import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.entity.QuestionWrapper;
import com.vhashiro.quizapp.entity.Quiz;
import com.vhashiro.quizapp.entity.UserResponse;
import com.vhashiro.quizapp.repository.QuestionRepository;
import com.vhashiro.quizapp.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = QuizServiceImpl.class)
class QuizServiceImplTest {

    @Autowired
    QuizServiceImpl quizService;

    @MockBean
    QuizRepository quizRepository;

    @MockBean
    QuestionRepository questionRepository;

    @Test
    void createQuiz() {
        // Mocking the repository's [return value]
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming"),
                new Question(2, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Programming"),
                new Question(2, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Java")
        );

        // when [questionRepository] return [mock return value]
        Mockito.when(questionRepository
                .findRandomQuestionsByQuestionCategory("Programming",2))
                .thenReturn(mockQuestions);

        // call [service method] to start TEST
        ResponseEntity<String> actualResult = quizService.createQuiz("Programming", 2, "2 Ques Quiz");



        /** Verify Results **/
        assertEquals(actualResult.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void getQuiz() {
        // Mocking the repository's [return value]
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming"),
                new Question(2, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Programming"),
                new Question(3, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Java")
        );
        Quiz quiz = new Quiz(1,mockQuestions,"Test Quiz");
        Optional<Quiz> quizOptional = Optional.of(quiz);

        // when [questionRepository] return [mock return value]
        Mockito.when(quizRepository.findById(1))
                .thenReturn(quizOptional);

        // call [service method] to start TEST
        ResponseEntity<List<QuestionWrapper>> actualResult = quizService.getQuiz(1);



        /** Verify Results **/
        assertEquals(actualResult.getStatusCode(), HttpStatus.OK);
        assertEquals(actualResult.getBody().get(0).getQuestionID(), 1);
        assertEquals(actualResult.getBody().get(1).getQuestionID(), 2);
    }

    @Test
    void calcResult() {
        // Mocking the method's [return values] and [necessities]
        List<UserResponse> mockUserResponse = Arrays.asList(
                new UserResponse(1, "Option1"),
                new UserResponse(2, "Option2"),
                new UserResponse(3, "Option3")
        );
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming"),
                new Question(2, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Programming"),
                new Question(3, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option3", "Java")
        );
        Quiz quiz = new Quiz(1,mockQuestions,"Test Quiz");
        Optional<Quiz> quizOptional = Optional.of(quiz);

        // when [questionRepository] return [mock return value]
        Mockito.when(quizRepository.findById(1)).thenReturn(quizOptional);

        // call [service method] to start TEST
        ResponseEntity<String> actualResult = quizService.calcResult(1,mockUserResponse);



        /** Verify Results **/
        assertEquals(actualResult.getStatusCode(), HttpStatus.OK);
        assertEquals(mockQuestions.get(0).getRightAnswer(), mockUserResponse.get(0).getSelectedOption(), "First user's answer should be correct");
        assertEquals(mockQuestions.get(1).getRightAnswer(), mockUserResponse.get(1).getSelectedOption(), "Second user's answer should be correct");
        assertEquals(mockQuestions.get(2).getRightAnswer(), mockUserResponse.get(2).getSelectedOption(), "Third user's answer should be correct");
    }
}