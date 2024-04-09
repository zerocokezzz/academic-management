package com.ezen.management.dto;

import com.ezen.management.domain.Student;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SubjectTestDTO {

    private Long idx;

    private Student student;

    private String subject;

    private String state;
}
