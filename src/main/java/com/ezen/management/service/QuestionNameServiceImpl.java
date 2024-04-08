package com.ezen.management.service;

import com.ezen.management.domain.QuestionName;
import com.ezen.management.repository.QuestionNameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionNameServiceImpl implements QuestionNameService {

    private final QuestionNameRepository questionNameRepository;
    @Override
    public List<QuestionName> findAll() {
        return questionNameRepository.findAll(Sort.by("name"));
    }

    @Override
    public void save(String name) {

        QuestionName questionName = new QuestionName();
        questionName.changeName(name);

        questionNameRepository.save(questionName);
    }

}
