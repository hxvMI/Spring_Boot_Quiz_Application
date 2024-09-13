package com.vhashiro.quizapp.entity;

import lombok.*;


//Doesn't have to be an ENTITY since we are not storing the UserResponse
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer id;
    private String selectedOption;

}
