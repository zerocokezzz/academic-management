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
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

//    설문 번호
    private int number;

//    회차
    private int round;

    @Column(nullable = false)
    private String item1;
    @Column(nullable = false)
    private String item2;
    @Column(nullable = false)
    private String item3;
    @Column(nullable = false)
    private String item4;
    @Column(nullable = false)
    private String item5;
    @Column(nullable = false)
    private String item6;
    @Column(nullable = false)
    private String item7;

}
