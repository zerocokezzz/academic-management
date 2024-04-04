package com.ezen.management.repository;

import com.ezen.management.domain.Counseling;
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
public class CounselingRepositoryTests {

    @Autowired
    private CounselingRepository counselingRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Test
    public void 상담추가(){

//        과정 고유 이름으로 과정 찾아옴
        Optional<Curriculum> findCurriculumById = curriculumRepository.findById(1L);
        Curriculum curriculum = findCurriculumById.orElseThrow();

        log.info("curriculum...... " + curriculum);

//        과정, 회차로 수업 찾아옴
        Optional<Lesson> byCurriculumAndNumber = lessonRepository.findById(1);
        Lesson lesson = byCurriculumAndNumber.orElseThrow();

        log.info("lesson...... " + lesson);

//        수업, 학생 이름으로 학생 찾아옴
        Optional<Student> studentResult = studentRepository.getByLessonAndName(lesson, "새별");
        Student student = studentResult.orElseThrow();

        log.info("student...... " + student);

//        학생 정보의 상담 컬럼 +1
        student.insertCounseling();
        studentRepository.save(student);

//        상담 DB 저장
        Counseling counseling = Counseling.builder()
                .content("자바에 회의감을 느낌")
                .method(1)
                .writer("교사2")
                .student(student)
                .build();

        counselingRepository.save(counseling);


    }
}
