package com.ezen.management.service;


import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Question;
import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.domain.Student;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.dto.QuestionAnswerDTO;
import com.ezen.management.repository.LessonRepository;

import com.ezen.management.repository.QuestionAnswerRepository;
import com.ezen.management.repository.QuestionRepository;
import com.ezen.management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionRepository questionRepository;
    private final StudentRepository studentRepository;

    private final LessonRepository lessonRepository;


    @Override
    @Transactional
    public int grading(QuestionAnswerDTO questionAnswerDTO) {


//        문제 리스트
        List<Question> questions = questionRepository.getQuestionsByName(questionAnswerDTO.getName());

//        답안지 저장
        QuestionAnswer questionAnswer = dtoToEntity(questionAnswerDTO);
        questionAnswerRepository.save(questionAnswer);

//      ---------------------------------------------------------------------------------

        questions.forEach(question -> {
            log.info("{}번 정답 : {}", question.getNumber(), question.getAnswer());
        });



//        답안지 리스트 만들기
        List<String> testPaper = questionAnswerDTO.getTestPaper();
        testPaper.forEach(log::info);
//        log.info("답안지" + testPaper);

//        채점
        int score = 0;
        for (int i = 0; i < testPaper.size(); i++) {
            if (Integer.parseInt(testPaper.get(i)) == questions.get(i).getAnswer()) {
                score += 5;
                log.info(i + "");
                log.info("점수 {} ", score);
            }
        }

//        학생 컬럼에 pretest -> true / score -> score
//        학생 찾기
        Optional<Student> findStudentById = studentRepository.findById(questionAnswerDTO.getStudentIdx());
        Student student = findStudentById.orElseThrow();

        student.grading(score);

        studentRepository.save(student);

        return 1;


    }


    @Override
    public PageResponseDTO<QuestionAnswer> findAll(Lesson lesson, String keyword, PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable();
        Page<QuestionAnswer> all = questionAnswerRepository.searchQuestionAnswer(lesson, keyword, pageable);
        List<QuestionAnswer> dtoList = all.getContent();

        return PageResponseDTO.<QuestionAnswer>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) all.getTotalElements())
                .build();

    }

    @Override
    public List<QuestionAnswer> findByLesson(Long lessonIdx) {
        Optional<Lesson> byId = lessonRepository.findById(lessonIdx);
        Lesson lesson = byId.orElseThrow();

        return questionAnswerRepository.findByLesson(lesson);

    }

    @Override
    public QuestionAnswerDTO findById(Long questionAnswerIdx) {
        Optional<QuestionAnswer> byId = questionAnswerRepository.findById(questionAnswerIdx);
        QuestionAnswer questionAnswer = byId.orElseThrow();

        return entityToDTO(questionAnswer);
    }

    @Override
    public QuestionAnswerDTO findByStudentIdx(Long studentIdx){


        QuestionAnswer byStudentIdx = questionAnswerRepository.findByStudentIdx(studentIdx);

        return entityToDTO(byStudentIdx);
    }


    QuestionAnswer dtoToEntity(QuestionAnswerDTO questionAnswerDTO) {

        Optional<Student> byId = studentRepository.findById(questionAnswerDTO.getStudentIdx());
        Student student = byId.orElseThrow();

        return QuestionAnswer.builder()
                .student(student)
                .name(questionAnswerDTO.getName())
                .an1(questionAnswerDTO.getAn1())
                .an2(questionAnswerDTO.getAn2())
                .an3(questionAnswerDTO.getAn3())
                .an4(questionAnswerDTO.getAn4())
                .an5(questionAnswerDTO.getAn5())
                .an6(questionAnswerDTO.getAn6())
                .an7(questionAnswerDTO.getAn7())
                .an8(questionAnswerDTO.getAn8())
                .an9(questionAnswerDTO.getAn9())
                .an10(questionAnswerDTO.getAn10())
                .an11(questionAnswerDTO.getAn11())
                .an12(questionAnswerDTO.getAn12())
                .an13(questionAnswerDTO.getAn13())
                .an14(questionAnswerDTO.getAn14())
                .an15(questionAnswerDTO.getAn15())
                .an16(questionAnswerDTO.getAn16())
                .an17(questionAnswerDTO.getAn17())
                .an18(questionAnswerDTO.getAn18())
                .an19(questionAnswerDTO.getAn19())
                .an20(questionAnswerDTO.getAn20())
                .build();
    }

    QuestionAnswerDTO entityToDTO(QuestionAnswer questionAnswer){

        Optional<QuestionAnswer> byId = questionAnswerRepository.findById(questionAnswer.getIdx());
        QuestionAnswer entity = byId.orElseThrow();

        return QuestionAnswerDTO.builder()
                .studentIdx(entity.getStudent().getIdx())
                .an1(entity.getAn1())
                .an2(entity.getAn2())
                .an3(entity.getAn3())
                .an4(entity.getAn4())
                .an5(entity.getAn5())
                .an6(entity.getAn6())
                .an7(entity.getAn7())
                .an8(entity.getAn8())
                .an9(entity.getAn9())
                .an10(entity.getAn10())
                .an11(entity.getAn11())
                .an12(entity.getAn12())
                .an13(entity.getAn13())
                .an14(entity.getAn14())
                .an15(entity.getAn15())
                .an16(entity.getAn16())
                .an17(entity.getAn17())
                .an18(entity.getAn18())
                .an19(entity.getAn19())
                .an20(entity.getAn20())
                .name(entity.getName())
                .build();

    }


}
