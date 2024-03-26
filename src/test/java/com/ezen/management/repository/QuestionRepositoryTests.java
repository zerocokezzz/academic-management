package com.ezen.management.repository;

import com.ezen.management.domain.Question;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class QuestionRepositoryTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LessonRepository lessonRepository;
    @Test
    public void 사전조사1문제추가(){

        Question question = Question.builder()
                .name("자바 풀스택")
                .number(1)
                .content("다음 중 니가 배우는 것은?")
                .item1("자바")
                .item2("파이썬")
                .item3("자바스크립트")
                .item4("C++")
                .answer(1)
                .build();

        questionRepository.save(question);


    }

    @Test
    public void 사전조사이름으로가져오기(){
        Optional<List<Question>> result = questionRepository.getQuestionsByName("자바 풀스택");
        List<Question> questions = result.orElseThrow();
        log.info("questions...... " + questions);
    }
}
