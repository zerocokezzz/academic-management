package com.ezen.management.service;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Student;
import com.ezen.management.domain.SurveyAnswer;
import com.ezen.management.dto.StudentDTO;
import com.ezen.management.dto.SurveyAnswerDTO;
import com.ezen.management.repository.LessonRepository;
import com.ezen.management.repository.StudentRepository;
import com.ezen.management.repository.SurveyAnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
//@Transactional //일단 보류해놓자
public class SurveyAnswerServiceImpl implements SurveyAnswerService{

    private final SurveyAnswerRepository surveyAnswerRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final TrainingService trainingService;


    @Override
    public int insert(SurveyAnswerDTO surveyAnswerDTO, StudentDTO studentDTO, int round) {

        // SurveyAnswerDTO에서 lesson_idx를 가져와서 Lesson 엔티티를 조회
        Optional<Lesson> lessonOptional = lessonRepository.findById(studentDTO.getLessonIdx());

        // Lesson 엔티티가 존재하는 경우에만 처리
        if (lessonOptional.isPresent()) {
            Lesson lesson = lessonOptional.get();

            surveyAnswerDTO.setLesson(lesson);

            // SurveyAnswerDTO를 SurveyAnswer 엔티티로 변환
            SurveyAnswer surveyAnswer = surveyAnswerDtoToEntity(surveyAnswerDTO);

            // SurveyAnswer를 저장
            surveyAnswerRepository.save(surveyAnswer);

            // Student 엔티티를 가져옴
            Optional<Student> studentOptional = studentRepository.findById(studentDTO.getIdx());

            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();

                student.insertSurvey();

                // 해당 round에 따라서 Survey 정보를 업데이트 -> studentEntity 활용해야 함
                studentRepository.save(student);

            } else {
                // Student not found
            }

            return 1;
        } else {
            // Lesson이 존재하지 않는 경우에 대한 처리를 추가

            return 0;
        }
    }
}
