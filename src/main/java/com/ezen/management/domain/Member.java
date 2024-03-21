package com.ezen.management.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int member_idx;

    private String id;
    private String pwd;

    private String name;
    private int access;


    @Builder.Default
    private String fileName = "default_profile.jpg";

    public void changeProfile(String fileName){
        this.fileName = fileName;
    }


}