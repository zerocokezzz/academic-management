package com.ezen.management.dto;

import com.ezen.management.domain.Lesson;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyAnswerDTO {

    // 설문 답변
    private Long idx;

    // 객관식 답변
    private int an1;
    private int an2;
    private int an3;
    private int an4;
    private int an5;
    private int an6;
    private int an7;
    private int an8;
    private int an9;
    private int an10;


    // 주관식 답변
    private String com1;
    private String com2;
    private String com3;
    private String com4;
    private String com5;
    private String com6;
    private String com7;
    private String com8;
    private String com9;
    private String com10;

}
