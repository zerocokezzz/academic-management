package com.ezen.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private int idx;
    private String name;
    private String content;
    private String example;
    private String fileName;
    private int number;
    private String item1;
    private String item2;
    private String item3;
    private String item4;

    private int answer;
}
