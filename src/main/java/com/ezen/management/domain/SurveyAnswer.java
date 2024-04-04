package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "lesson")
public class SurveyAnswer {//    설문 답변

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne
    private Lesson lesson;

//    객관식 답변
    @Column(nullable = false)
    private int an1;
    @Column(nullable = false)
    private int an2;
    @Column(nullable = false)
    private int an3;
    @Column(nullable = false)
    private int an4;
    @Column(nullable = false)
    private int an5;
    @Column(nullable = false)
    private int an6;
    @Column(nullable = false)
    private int an7;
    @Column(nullable = false)
    private int an8;
    @Column(nullable = false)
    private int an9;
    @Column(nullable = false)
    private int an10;


//    주관식 답변
    private String com1;
    private String com2;
    private String com3;
    private String com4;
    private String com5;
    private String com6;
    private String com7;
    private String com8;
    private String com9;
    private String com10;



}
