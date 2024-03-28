package com.ezen.management.service;

import com.ezen.management.domain.Question;
import com.ezen.management.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;


    @Override
    public List<Question> findQuestionByName(String questionName) {
        return questionRepository.getQuestionsByName(questionName);
    }
}
