package com.ezen.management.repository;

import com.ezen.management.domain.Category;
import com.ezen.management.domain.Curriculum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class CurriculumRepositoryTests {

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void 과정추가(){

        Optional<Category> result = categoryRepository.findById(1L);
        Category category = result.orElseThrow();

        Curriculum curriculum = Curriculum.builder()
                .category(category)
                .name("풀스택 프레임워크(자바,스프링)기반 데이터 융합SW개발자 과정 ")
                .day(120)
                .time(960)
                .build();

        curriculumRepository.save(curriculum);

    }
}
