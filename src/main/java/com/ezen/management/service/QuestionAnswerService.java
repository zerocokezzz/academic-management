package com.ezen.management.service;

import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.dto.QuestionAnswerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionAnswerService {

    int grading(QuestionAnswerDTO questionAnswerDTO);

    PageResponseDTO<QuestionAnswer> findAll(String keyword, PageRequestDTO pageRequestDTO);

    List<QuestionAnswer> findByLesson(int lessonIdx);



}
