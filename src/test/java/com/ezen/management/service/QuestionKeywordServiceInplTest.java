package com.ezen.management.service;

import com.ezen.management.domain.QuestionKeyword;
import com.ezen.management.repository.QuestionKeywordRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class QuestionKeywordServiceInplTest {

    @Autowired
    QuestionKeywordService questionKeywordService;

    @Test
    public void 전체목록() throws Exception {
        //given
        List<QuestionKeyword> all = questionKeywordService.findAll();

        //when
        all.forEach(questionKeyword -> {
            log.info(questionKeyword.getName());
        });

        //then

    }

}