package com.ezen.management.service;

import com.ezen.management.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class StudentServiceTests {

    @Autowired
    StudentService studentService;

    @Test
    public void 학생선택() throws Exception {
        //given

        //when
        Student byId = studentService.findById(1L);
        log.info("student ... {}", byId);

        //then
        Assertions.assertThat(byId).isNotNull();

    }
}
