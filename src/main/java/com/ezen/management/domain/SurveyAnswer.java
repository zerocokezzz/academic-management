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
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    private Lesson lesson;

    private int round;

    //    객관식 답변
    @Column(nullable = false)
    private String an1;
    @Column(nullable = false)
    private String an2;
    @Column(nullable = false)
    private String an3;
    @Column(nullable = false)
    private String an4;
    @Column(nullable = false)
    private String an5;
    @Column(nullable = false)
    private String an6;
    @Column(nullable = false)
    private String an7;
    @Column(nullable = false)
    private String an8;
    @Column(nullable = false)
    private String an9;
    @Column(nullable = false)
    private String an10;
    @Column(nullable = false)
    private String an11;
    @Column(nullable = false)
    private String an12;
    @Column(nullable = false)
    private String an13;
    @Column(nullable = false)
    private String an14;
    @Column(nullable = false)
    private String an15;

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
    private String com11;
    private String com12;
    private String com13;
    private String com14;
    private String com15;

}