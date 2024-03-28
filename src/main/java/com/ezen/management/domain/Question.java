package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

//    주제
//    동일한 주제가 20개 -> 한 수업의 문제가 됨
    private String name;

//    문제
    private String content;
//    보기
    private String example;
//    사진
    private String fileName;

    private int number;

    private String answer;

    private String item1;
    private String item2;
    private String item3;
    private String item4;


}
