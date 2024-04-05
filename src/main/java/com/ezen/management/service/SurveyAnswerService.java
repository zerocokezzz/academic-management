package com.ezen.management.service;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Survey;
import com.ezen.management.domain.SurveyAnswer;
import com.ezen.management.dto.StudentDTO;
import com.ezen.management.dto.SurveyAnswerDTO;
import com.ezen.management.dto.SurveyDTO;

public interface SurveyAnswerService {

    public int insert(SurveyAnswerDTO surveyAnswerDTO, StudentDTO studentDTO, int round);

    default SurveyAnswer surveyAnswerDtoToEntity(SurveyAnswerDTO surveyAnswerDTO){

        SurveyAnswer surveyAnswer = SurveyAnswer.builder()
                .idx(surveyAnswerDTO.getIdx())
                .an1(surveyAnswerDTO.getAn1())
                .an2(surveyAnswerDTO.getAn2())
                .an3(surveyAnswerDTO.getAn3())
                .an4(surveyAnswerDTO.getAn4())
                .an5(surveyAnswerDTO.getAn5())
                .an6(surveyAnswerDTO.getAn6())
                .an7(surveyAnswerDTO.getAn7())
                .an8(surveyAnswerDTO.getAn8())
                .an9(surveyAnswerDTO.getAn9())
                .an10(surveyAnswerDTO.getAn10())
                .com1(surveyAnswerDTO.getCom1())
                .com2(surveyAnswerDTO.getCom2())
                .com3(surveyAnswerDTO.getCom3())
                .com4(surveyAnswerDTO.getCom4())
                .com5(surveyAnswerDTO.getCom5())
                .com6(surveyAnswerDTO.getCom6())
                .com7(surveyAnswerDTO.getCom7())
                .com8(surveyAnswerDTO.getCom8())
                .com9(surveyAnswerDTO.getCom9())
                .com10(surveyAnswerDTO.getCom10())
                .build();

        return surveyAnswer;
    }
}
