package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Counseling extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Student student;

    @Column(nullable = false)
    private LocalDateTime counselingDate;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
//    0은 대면, 1 전화, 2 온라인
    private int method;

    //상담회차
    private int round;

    public void changeContent(int method, String content, int round, String writer){
        this.method = method;
        this.content = content;
        this.round = round;
        this.writer = writer;
    }

}
