package com.vhashiro.quizapp.controller;

import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.entity.QuestionWrapper;
import com.vhashiro.quizapp.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Plain Mockito library for testing **/
class QuestionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    void setUp() {
        // To enable Mockito annotations during test executions
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    void testStartGetQuestionsList() throws Exception {
        // Create a mock list of questions
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming"),
                new Question(2, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Framework")
        );

        // Mock the service response
        Mockito.when(questionService.getQuestionsList())
                .thenReturn(new ResponseEntity<>(mockQuestions, HttpStatus.OK));

        // Perform a GET request to /question/allQuestions
        /** GET request doesn't need to include .content() **/
        mockMvc.perform(get("/question/allQuestions"))
                .andExpect(status().isOk());



        /** Verify response with JUNIT using @InjectMocks controller **/
        ResponseEntity<List<Question>> response = questionController.startGetQuestionsList();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testStartGetQuestionsListCategory() throws Exception {
        // Create a mock list of questions in a specific category
        List<Question> mockQuestions = Arrays.asList(
                new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming")
        );

        // Mock the service response
        Mockito.when(questionService.getQuestionsListCategory("Programming"))
                .thenReturn(new ResponseEntity<>(mockQuestions, HttpStatus.OK));

        // Send GET request to /question/category/Programming
        mockMvc.perform(get("/question/category/Programming"))
                .andExpect(status().isOk());



        /** Verify response with JUNIT using @InjectMocks controller **/
        ResponseEntity<List<Question>> response = questionController.startGetQuestionsListCategory("Programming");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Programming", response.getBody().get(0).getQuestionCategory());
    }

    @Test
    void testStartAddQuestion() throws Exception {
        // Mock String to return from ResponseEntity<String> from .addQuestion
        String mockString = "Test Question Created";

        // When .addQuestion is called return this instead
        Mockito.when(questionService.addQuestion(Mockito.any(Question.class)))
                .thenReturn(new ResponseEntity<>(mockString, HttpStatus.CREATED));

        // Send a request with the following content to /question/add
        /** Since we are sending a POST request we need to include .content() **/
        mockMvc.perform(post("/question/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"questionID\":1,\"difficultyLevel\":1,\"questionName\":\"What is Java?\",\"option1\":\"Option1\",\"option2\":\"Option2\",\"option3\":\"Option3\",\"option4\":\"Option4\",\"rightAnswer\":\"Option1\",\"questionCategory\":\"Programming\"}"))
                .andExpect(status().isCreated());


        /** Verify response with JUNIT using @InjectMocks controller **/
        ResponseEntity<String> response = questionController.startAddQuestion(
                new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
