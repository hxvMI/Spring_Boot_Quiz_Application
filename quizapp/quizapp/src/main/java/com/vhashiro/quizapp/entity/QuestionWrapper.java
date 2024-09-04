package com.vhashiro.quizapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionID;     /** for Oracle use GenerationType.IDENTITY */

    private String questionName;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
