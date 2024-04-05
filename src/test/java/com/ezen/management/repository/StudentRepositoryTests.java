package com.ezen.management.repository;

import com.ezen.management.domain.Curriculum;
import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Test
    public void 학생추가(){

        Optional<Curriculum> result = curriculumRepository.findById(1L);

        Curriculum curriculum = result.orElseThrow();

//        파라미터 (수업, 회차)
        Optional<Lesson> lessonResult = lessonRepository.getByCurriculumAndNumber(curriculum, 1);
        Lesson lesson = lessonResult.orElseThrow();

        Student student = Student.builder()
                .lesson(lesson)
                .name("지혜")
                .email("gongong@gmail.com")
                .birthday("000000")
                .build();

        studentRepository.save(student);


    }

    @Test
    public void 학생선택(){
        
        int lessonIdx = 1;
        Optional<Lesson> result = lessonRepository.findById(1L);
        Lesson lesson = result.orElseThrow();

        Optional<Student> byLessonAndName = studentRepository.getByLessonAndName(lesson, "새별");
        Student student = byLessonAndName.orElseThrow();
        log.info("student......" + student);
    }

    @Test
    public void 학생선택2(){

        Optional<Curriculum> result = curriculumRepository.findById(1L);
        Curriculum curriculum = result.orElseThrow();

        Optional<Lesson> lessonResult = lessonRepository.getByCurriculumAndNumber(curriculum, 1);
        Lesson lesson = lessonResult.orElseThrow();

        Student student = Student.builder()
                .lesson(lesson)
                .name("혜연")
                .email("yeon@gmail.com")
                .birthday("970000")
                .etc("비고입니다.")
                .build();

        studentRepository.save(student);


    }
}
