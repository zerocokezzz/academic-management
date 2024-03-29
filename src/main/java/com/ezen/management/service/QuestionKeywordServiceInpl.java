package com.ezen.management.service;

import com.ezen.management.domain.QuestionKeyword;
import com.ezen.management.repository.QuestionKeywordRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionKeywordServiceInpl implements QuestionKeywordService{

    private final QuestionKeywordRepository questionKeywordRepository;
    @Override
    public List<QuestionKeyword> findAll() {
        return questionKeywordRepository.findAll(Sort.by("name"));
    }

}
