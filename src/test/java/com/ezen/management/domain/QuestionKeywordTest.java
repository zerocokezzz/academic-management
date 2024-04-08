package com.ezen.management.domain;

import com.ezen.management.repository.QuestionNameRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class QuestionKeywordTest {

    @Autowired
    QuestionNameRepository questionKeywordRepository;

    @Test
    public void 문제키워드추가() throws Exception {
        //given
        QuestionName questionKeyword = QuestionName.builder()
                .name("프론트엔드")
                .build();

        questionKeywordRepository.save(questionKeyword);

        //when
        Optional<QuestionName> result = questionKeywordRepository.findById("프론트엔드");
        QuestionName findById = result.orElseThrow();

        //then
        Assertions.assertThat(findById.getName()).isEqualTo(questionKeyword.getName());

    }

    @Test
    public void 전체목록() throws Exception {
        //given
        List<QuestionName> all = questionKeywordRepository.findAll();

        //when
        all.forEach(questionKeyword -> {
            log.info(questionKeyword.getName());
        });

        //then

    }

}