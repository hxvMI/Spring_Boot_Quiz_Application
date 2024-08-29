package com.vhashiro.quizapp.repository;

import com.vhashiro.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public Question findByQuestionId(Long questionId);

}
