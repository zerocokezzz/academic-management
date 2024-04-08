package com.ezen.management.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LessonDTO {

    private Long idx;

    private Long curriculum_idx;

    private String curriculum_name;

    private int curriculum_time;

    private int curriculum_day;

    private String member_id;

    private String member_name;

    private int number;

    private int headCount;

    private LocalDate startDay;

    private LocalDate endDay;

    private LocalDate survey1;

    private String content;

    private LocalDate survey2;

    private LocalDate survey3;

    private String classRoom;

    private String questionName;

}
