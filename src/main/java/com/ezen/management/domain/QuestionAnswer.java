package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(exclude = "student")
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @OneToOne
    private Student student;

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
    @Column(nullable = false)
    private String an16;
    @Column(nullable = false)
    private String an17;
    @Column(nullable = false)
    private String an18;
    @Column(nullable = false)
    private String an19;
    @Column(nullable = false)
    private String an20;
}
