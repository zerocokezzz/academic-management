package com.ezen.management.dto;

import lombok.*;
import org.modelmapper.internal.bytebuddy.build.RepeatedAnnotationPlugin;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CounselingStudentDTO {


    private Long counselingIdx;             //상담idx
    private Long studentIdx;                //학생idx
    private String name;                    //학생이름
    private String fileName;                //학생사진
    private LocalDateTime counselingDate;   //상담일자
    private String content;                 //내용
    private int method;                     //방식
    private String writer;                  //담당자
    private LocalDateTime regDate;          //추가날짜
    private LocalDateTime modDate;          //수정날짜


}
