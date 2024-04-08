package com.ezen.management.dto;

import com.ezen.management.domain.Category;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurriculumDTO {

    private Long idx;

    private String name;

    private Category category;

    private int time;

    private int day;

}
