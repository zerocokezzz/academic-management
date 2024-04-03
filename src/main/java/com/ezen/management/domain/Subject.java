package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    //과목명
    private String name;

    //평가방식
    @Column(nullable = false)
    private String method;

    public void changeMethod(String method){this.method = method;}
}