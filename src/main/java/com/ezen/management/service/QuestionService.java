package com.ezen.management.service;

import com.ezen.management.domain.Question;
import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.dto.QuestionAnswerDTO;
import com.ezen.management.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<Question> findQuestionByName(String questionName);
    List<Question> findAll();

    Question findById(int questionIdx);
    int insert(QuestionDTO questionDTO);

    int update(QuestionDTO questionDTO);

    void delete(int questionIdx);

    void multiSave(List<QuestionDTO> questionDTOList);

}
