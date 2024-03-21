package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne
    private Curriculum curriculum;

    @ManyToOne
//    교사
    private Member member;

    //    기수
    private int number;

    //    인원
    @Builder.Default
    private int head_count = 0;

    private LocalDate startDay;
    private LocalDate endDay;

    private LocalDate survey1;
    private LocalDate survey2;
    private LocalDate survey3;

    private String classRoom;

    public void changeTeacher(Member member){
        this.member = member;
    }


}