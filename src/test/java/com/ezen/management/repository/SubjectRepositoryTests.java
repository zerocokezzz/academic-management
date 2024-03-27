package com.ezen.management.repository;

import com.ezen.management.domain.Subject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class SubjectRepositoryTests {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void 등록(){
        Subject subject = Subject.builder()
                .name("자바")
                .method("시험")
                .build();

        subjectRepository.save(subject);
    }

    @Test
    public void 수정(){
        Optional<Subject> result = subjectRepository.findById("자바");

        Subject subject = result.orElseThrow();
        subject.changeMethod("팀프로젝트");

        subjectRepository.save(subject);
    }

    @Test
    public void 전체보기(){

    }

    @Test
    public void 하나보기(){}

    @Test
    public void 삭제(){}
}
