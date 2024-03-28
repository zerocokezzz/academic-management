package com.ezen.management.dto;

import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.domain.Student;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionAnswerDTO {

//    private Student student;

    private int studentIdx;

//    ex) 자바 풀스택
    private String name;

    private String an1;
    private String an2;
    private String an3;
    private String an4;
    private String an5;
    private String an6;
    private String an7;
    private String an8;
    private String an9;
    private String an10;
    private String an11;
    private String an12;
    private String an13;
    private String an14;
    private String an15;
    private String an16;
    private String an17;
    private String an18;
    private String an19;
    private String an20;


    public List<String> getTestPaper(){
        List<String> testPaper = new ArrayList<>();
        testPaper.add(an1);
        testPaper.add(an2);
        testPaper.add(an3);
        testPaper.add(an4);
        testPaper.add(an5);
        testPaper.add(an6);
        testPaper.add(an7);
        testPaper.add(an8);
        testPaper.add(an9);
        testPaper.add(an10);
        testPaper.add(an11);
        testPaper.add(an12);
        testPaper.add(an13);
        testPaper.add(an14);
        testPaper.add(an15);
        testPaper.add(an16);
        testPaper.add(an17);
        testPaper.add(an18);
        testPaper.add(an19);
        testPaper.add(an20);

        return testPaper;

    }



}
