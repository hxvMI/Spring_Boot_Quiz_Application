package com.vhashiro.quizapp.controller;

import com.vhashiro.quizapp.entity.QuestionWrapper;
import com.vhashiro.quizapp.entity.UserResponse;
import com.vhashiro.quizapp.service.QuizService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class QuizControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
    }

    @Test
    void startCreateQuiz() throws Exception {

        /** USED to link multiple @RequestParam's **/
        LinkedMultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
        reqParams.add("questionCategory", "Programming");
        reqParams.add("numQuestions", "2");
        reqParams.add("title", "TestQuiz");

        //When .createQuiz is called return a PRESET ResponseEntity<>
        Mockito.when(quizService.createQuiz("Programming",2,"TestQuiz"))
                .thenReturn(new ResponseEntity<>("Created new Quiz", HttpStatus.CREATED));

        /** INPUT for .createQuiz() are @RequestParam
            so we use .params(reqParams) as INPUT content **/
        mockMvc.perform(post("/quiz/create")
                .params(reqParams))
                .andExpect(status().isCreated());


        /** Testing STATUS response with JUNIT **/
        ResponseEntity<String> actualResult = quizController.startCreateQuiz("Programming",2,"TestQuiz");
        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
    }

    @Test
    void startGetQuiz() throws Exception {

        // Fake quiz full of Questions
        List<QuestionWrapper> quizQuestions = Arrays.asList(
                new QuestionWrapper(1, "What is Java?", "Option1", "Option2", "Option3", "Option4"),
                new QuestionWrapper(2, "What is Spring?", "Option1", "Option2", "Option3", "Option4")
        );

        // Mock service response to .getQuiz()
        Mockito.when(quizService.getQuiz(1))
                .thenReturn(new ResponseEntity<>(quizQuestions,HttpStatus.OK));

        // Send GET request to /quiz/get/{id}
        mockMvc.perform(get("/quiz/get/1"))
                .andExpect(status().isOk());



        /** Testing with JUNIT
            STATUS response, # of questions in QUIZ, question ID of questions in QUIZ   **/
        ResponseEntity<List<QuestionWrapper>> actualResult = quizController.startGetQuiz(1);
        assertEquals(2,actualResult.getBody().size());
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        assertEquals(1,actualResult.getBody().get(0).getQuestionID());
    }

    @Test
    void submitQuiz() throws Exception {

        // Create a fake list of UserResponse
        List<UserResponse> userResponses = Arrays.asList(
                new UserResponse(1, "potato"),
                new UserResponse(2, "tomato")
        );

        // Mock a response to .calcResult() method
        Mockito.when(quizService.calcResult(1, userResponses))
                .thenReturn(new ResponseEntity<>("Score is 2/2",HttpStatus.OK));

        // Send POST request to /quiz/submit/{id}
        /** @PathVariable is set via URL VVVV
            we use @RequestBody as INPUT from USER so .contentType().content() is used  **/
        mockMvc.perform(post("/quiz/submit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"id\":1,\"selectedOption\":\"Option1\"},{\"id\":2,\"selectedOption\":\"Option2\"}]"))
                .andExpect(status().isOk());


        /** Verify response with JUNIT using @InjectMocks controller **/
        ResponseEntity<String> actualResult = quizController.submitQuiz(1, userResponses);
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }
}