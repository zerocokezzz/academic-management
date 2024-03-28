package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "lesson")
public class Student extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne
    private Lesson lesson;

    private String name;
    private String birthday;
    private String email;

    @Builder.Default
    private String fileName = "default_profile.jpg";

//    상담 등록 시 +1
    @Builder.Default
    private int counseling = 0;

//    사전 평가 여부
//    사전 평가 등록 시 +1
    @Builder.Default
    private boolean pretest = false;

//    사전 평가 점수
    @Builder.Default
    private int score = 0;

//    설문 조사 여부
//    설문 조사 등록 시 true
    @Builder.Default
    private boolean survey1 = false;
    @Builder.Default
    private boolean survey2 = false;
    @Builder.Default
    private boolean survey3 = false;

    @Builder.Default
//    0 진행중 / 1 수료 / 2 하차
    private int done = 0;




    private String etc;

    public void insertCounseling(){
        this.counseling++;
    }

    public void grading(int score){
        this.pretest = true;
        this.score = score;
    }


}