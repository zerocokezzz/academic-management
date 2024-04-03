package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class QuestionName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false)
    private String name;

    public void changeName(String name){
        this.name = name;
    }

}
