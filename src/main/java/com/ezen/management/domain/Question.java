package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

//    주제
//    동일한 주제가 20개 -> 한 수업의 문제가 됨
    private String name;

//    문제 내용
    private String content;

    private int number;

    private int answer;

    private String item1;
    private String item2;
    private String item3;
    private String item4;


}
