package com.ezen.management.service;

import com.ezen.management.domain.Question;
import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.dto.QuestionAnswerDTO;

import java.util.List;

public interface QuestionService {

    List<Question> findQuestionByName(String questionName);
    List<Question> findAll();


}
