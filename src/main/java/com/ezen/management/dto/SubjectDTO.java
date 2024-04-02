package com.ezen.management.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubjectDTO {

    private Long idx;
    
    //평가방법
    @NotNull
    private String method;
    
    //과목명
    @NotNull
    private String name;


}
