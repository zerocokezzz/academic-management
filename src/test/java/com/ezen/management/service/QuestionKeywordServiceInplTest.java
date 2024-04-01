package com.ezen.management.service;

import com.ezen.management.domain.QuestionName;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class QuestionKeywordServiceInplTest {

    @Autowired
    QuestionNameService questionKeywordService;

    @Test
    public void 전체목록() throws Exception {
        //given
        List<QuestionName> all = questionKeywordService.findAll();

        //when
        all.forEach(questionKeyword -> {
            log.info(questionKeyword.getName());
        });

        //then

    }

}