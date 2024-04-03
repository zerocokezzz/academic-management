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
                .number(20)
                .content("다음 SQL 명령문의 의미로 가장 적절한 것은?")
                .example("DROP TABLE 학과 CASCADE")
                .item1("학과 테이블을 제거")
                .item2("학과 필드를 제거")
                .item3("학과 테이블과 이 테이블을 참조하는 다른 테이블도 함께 제거")
                .item4("학과 테이블이 다른 테이블에 참조 중이면 제거하지 않음")
                .answer(3)
                .build();

        questionRepository.save(question);


    }

    @Test
    public void 사전조사이름으로가져오기(){
        List<Question> questions = questionRepository.getQuestionsByName("자바 풀스택");
//        log.info("questions...... " + questions);

        questions.forEach(question -> {
            log.info(question.getNumber() + "");
            log.info(question.getContent());
        });
    }

    @Test
    public void 무작위문제넣기() throws Exception {
        //given
        for(int i = 1; i <= 20; i++) {
            Question question = Question.builder()
                    .name("자바 풀스택")
                    .number(i)
                    .content("다음 중 마음에 드는 것을 고르시오.")
                    .example("설명입니다.")
                    .item1("1번입니다.")
                    .item2("2번입니다.")
                    .item3("3번입니다.")
                    .item4("4번입니다.")
                    .answer(1)
                    .build();
            questionRepository.save(question);
        }

        //when

        //then


    }
}
