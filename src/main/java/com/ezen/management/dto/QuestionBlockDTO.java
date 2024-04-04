package com.ezen.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionBlockDTO {

    private String name;
    private List<QuestionDTO> questionDTOList;
}
