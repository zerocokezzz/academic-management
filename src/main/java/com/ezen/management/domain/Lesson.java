package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "curriculum")
public class Lesson extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Curriculum curriculum;

    @ManyToOne
//    교사
    private Member member;

    @Column(nullable = false)
    //    기수
    private int number;

    //    인원
    @Builder.Default
    private int head_count = 0;

    @Column(nullable = false)
    private LocalDate startDay;

    @Column(nullable = false)
    private LocalDate endDay;

    @Column(nullable = false)
    private LocalDate survey1;

    @Column(nullable = false)
    private LocalDate survey2;

    @Column(nullable = false)
    private LocalDate survey3;

    private String classRoom;

    public void changeTeacher(Member member){
        this.member = member;
    }

    public void changeClassroom(String classRoom) {
        this.classRoom = classRoom;
    }


}