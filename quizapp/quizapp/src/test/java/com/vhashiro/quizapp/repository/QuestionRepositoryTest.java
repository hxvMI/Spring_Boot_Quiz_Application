package com.vhashiro.quizapp.repository;

import com.vhashiro.quizapp.entity.Question;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        // Mock question objects for [Repo Method Testing]
        Question question1 = new Question(1, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming");
        Question question2 = new Question(2, 2, "What is Spring?", "Option1", "Option2", "Option3", "Option4", "Option2", "Framework");
        Question question3 = new Question(3, 1, "What is Java?", "Option1", "Option2", "Option3", "Option4", "Option1", "Programming");


        // Merge instead of persist to handle detached entity issue
        entityManager.merge(question1);
        entityManager.merge(question2);
        entityManager.merge(question3);

        // Flush to ensure data is written to database before test runs
        entityManager.flush();
    }

    @Test
    void findByQuestionCategory() {
        // Calling Repo method
        List<Question> questions = questionRepository
                .findByQuestionCategory("Programming");



        /** verifying results **/
        assertFalse(questions.isEmpty(), "Questions list should not be empty");
        assertEquals("Programming", questions.get(0).getQuestionCategory());
    }

    @Test
    void findRandomQuestionsByQuestionCategory() {
        // Calling Repo method
        List<Question> questions = questionRepository
                .findRandomQuestionsByQuestionCategory("Programming",2);



        /** verifying results **/
        assertFalse(questions.isEmpty(), "Questions list should not be empty");
        assertEquals("Programming", questions.get(0).getQuestionCategory());
        assertEquals(questions.size(), 2);
    }
}