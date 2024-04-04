package com.ezen.management.service;

import com.ezen.management.domain.Question;

import com.ezen.management.dto.QuestionDTO;
import com.ezen.management.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<Question> findQuestionByName(String questionName) {
        return questionRepository.getQuestionsByName(questionName);
    }


    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(int questionIdx) {
        Optional<Question> byId = questionRepository.findById(questionIdx);
        return byId.get();
    }

    @Override
    public int insert(QuestionDTO questionDTO) {

        Question question = modelMapper.map(questionDTO, Question.class);
        questionRepository.save(question);

        return 1;
    }

    @Override
    public int update(QuestionDTO questionDTO) {

        Question byId = findById(questionDTO.getIdx());
        byId.changeContent(questionDTO.getContent());
        byId.changeExample(questionDTO.getExample());
//        파일 고치는 로직은 따로?
        byId.changeItem(questionDTO.getItem1(), questionDTO.getItem2(), questionDTO.getItem3(), questionDTO.getItem4());

        Question save = questionRepository.save(byId);

        return 1;
    }

    @Override
    public void delete(int questionIdx) {
        Optional<Question> byId = questionRepository.findById(questionIdx);
        Question question = byId.orElseThrow();

        questionRepository.delete(question);

    }
}
