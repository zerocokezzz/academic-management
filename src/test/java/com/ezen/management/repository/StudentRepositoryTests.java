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
    public void insertTest(){

        Optional<Curriculum> result = curriculumRepository.findById("풀스택 프레임워크(자바,스프링)기반 데이터 융합SW개발자 과정");
        Curriculum curriculum = result.orElseThrow();

        Optional<Lesson> lessonResult = lessonRepository.getByCurriculumAndNumber(curriculum, 1);
        Lesson lesson = lessonResult.orElseThrow();

        Student student = Student.builder()
                .lesson(lesson)
                .name("새별")
                .email("byeol@gmail.com")
                .birthday("960123")
                .build();

        studentRepository.save(student);


    }

    @Test
    public void selectOne(){
        
        int lessonIdx = 1;
        Optional<Lesson> result = lessonRepository.findById(1);
        Lesson lesson = result.orElseThrow();

        Optional<Student> byLessonAndName = studentRepository.getByLessonAndName(lesson, "새별");
        Student student = byLessonAndName.orElseThrow();
        log.info("student......" + student);
    }

    @Test
    public void insertTest2(){

        Optional<Curriculum> result = curriculumRepository.findById("풀스택 프레임워크(자바,스프링)기반 데이터 융합SW개발자 과정");
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
