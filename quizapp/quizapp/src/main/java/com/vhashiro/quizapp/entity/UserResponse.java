package com.vhashiro.quizapp.entity;

import lombok.*;


@Data
@RequiredArgsConstructor
public class UserResponse {

    private Integer id;
    private String selectedOption;

}
