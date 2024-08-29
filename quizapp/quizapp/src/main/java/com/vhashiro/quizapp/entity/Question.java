package com.vhashiro.quizapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionID;     /** for Oracle use GenerationType.IDENTITY */
    private int difficultyLevel;    /** 1 = lowest    3 = highest */

    private String questionName;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;

}
