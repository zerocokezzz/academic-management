package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

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

    @Builder.Default
    private int counseling = 0;

    @Builder.Default
    private boolean pretest = false;

    @Builder.Default
    private int score = 0;

    @Builder.Default
    private boolean survey1 = false;
    @Builder.Default
    private boolean survey2 = false;
    @Builder.Default
    private boolean survey3 = false;

    @Builder.Default
    private int done = 0;

}