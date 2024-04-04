package com.ezen.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SurveyDtoList implements Iterable<SurveyDTO> {
    private List<SurveyDTO> surveyDtoList;

    public SurveyDtoList() {
        surveyDtoList = new ArrayList<>();
    }

    @Override
    public Iterator<SurveyDTO> iterator() {
        return surveyDtoList.iterator();
    }

}
