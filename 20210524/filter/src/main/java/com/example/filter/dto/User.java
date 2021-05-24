package com.example.filter.dto;

import lombok.*;

// @Getter // 변수에 게터 함수를 만들어준다.
// @Setter // 변수에 세터 함수를 만들어준다.

//Getter Setter toString 을 다 만들어주는 annotation이 있다
@Data
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 전체 생성자
public class User {

    private String name;
    private int age;


}
