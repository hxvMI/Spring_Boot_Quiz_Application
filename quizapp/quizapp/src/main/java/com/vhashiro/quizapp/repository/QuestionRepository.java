package com.vhashiro.quizapp.repository;

import com.vhashiro.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByQuestionCategory(String questionCategory);

    //                                           vDataBase Name    vVariableName
    @Query(value = "SELECT * FROM question WHERE question_category=:questionCategory ORDER BY RAND() LIMIT :numQuestions",
            nativeQuery = true)
    List<Question> findRandomQuestionsByQuestionCategory(String questionCategory, int numQuestions);

}
