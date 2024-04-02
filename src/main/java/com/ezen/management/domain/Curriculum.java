package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;

    @ManyToOne
    private Category category;

    private int time;
    private int day;

    public void changeName(String putName){this.name = putName;}

    public void changeCategory(Category category){this.category = category; }

    public void changeTime(int time){this.time = time; }

    public void changeDay(int day){this.day = day; }

}
