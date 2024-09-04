package com.vhashiro.quizapp.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer quizId;

    /** association mapping needed to interact with another
     * @Entity or Database Table */
    /* MANY different <Question> table records in
       MANY different <Quiz> table records */
    @ManyToMany
    private List<Question> questionsList;
    private String title;


}
