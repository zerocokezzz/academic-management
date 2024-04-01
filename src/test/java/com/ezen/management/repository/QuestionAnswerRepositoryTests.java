package com.ezen.management.repository;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class QuestionAnswerRepositoryTests {

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Test
    public void 사전조사등록(){

        Optional<Lesson> byId = lessonRepository.findById(1);
        Lesson lesson = byId.orElseThrow();

        log.info("lesson...... " + lesson);

        Optional<Student> byLessonAndName = studentRepository.getByLessonAndName(lesson, "새별");
        Student student = byLessonAndName.orElseThrow();

        log.info("student...... " + student);

        QuestionAnswer questionAnswer = QuestionAnswer.builder()
                .student(student)
                .an1("1")
                .an2("1")
                .an3("1")
                .an4("1")
                .an5("1")
                .an6("1")
                .an7("1")
                .an8("1")
                .an9("1")
                .an10("1")
                .an11("1")
                .an12("1")
                .an13("1")
                .an14("1")
                .an15("1")
                .an16("1")
                .an17("1")
                .an18("1")
                .an19("1")
                .an20("1")
                .build();

        questionAnswerRepository.save(questionAnswer);
    }

    @Test
    public void 채점(){

        Optional<Lesson> byId = lessonRepository.findById(1);
        Lesson lesson = byId.orElseThrow();
        Optional<Student> byLessonAndName = studentRepository.getByLessonAndName(lesson, "새별");
        Student student = byLessonAndName.orElseThrow();

        Optional<QuestionAnswer> byId1 = questionAnswerRepository.findById(1);
        QuestionAnswer questionAnswer = byId1.orElseThrow();

        List<String> answerList = new ArrayList<>();

//        아니 이걸 애초에 arrayList로 만들었으면 이렇게 안 해도 되는 거 아닌가? ㅠㅠ
        answerList.add(questionAnswer.getAn1());
        answerList.add(questionAnswer.getAn2());
        answerList.add(questionAnswer.getAn3());
        answerList.add(questionAnswer.getAn4());
        answerList.add(questionAnswer.getAn5());
        answerList.add(questionAnswer.getAn6());
        answerList.add(questionAnswer.getAn7());
        answerList.add(questionAnswer.getAn8());
        answerList.add(questionAnswer.getAn9());
        answerList.add(questionAnswer.getAn10());
        answerList.add(questionAnswer.getAn11());
        answerList.add(questionAnswer.getAn12());
        answerList.add(questionAnswer.getAn13());
        answerList.add(questionAnswer.getAn14());
        answerList.add(questionAnswer.getAn15());
        answerList.add(questionAnswer.getAn16());
        answerList.add(questionAnswer.getAn17());
        answerList.add(questionAnswer.getAn18());
        answerList.add(questionAnswer.getAn10());
        answerList.add(questionAnswer.getAn20());

        int score = 0;

        for(String answer : answerList){
//            여기가 정답 리스트랑 비교하는 부분
            if(answer.equals("1")){
                score = score + 5;
            }
        }

        log.info("score...... " + score);

    }

    @Test
    public void 전체목록() throws Exception {
        //given
        Pageable pageable = PageRequest.of(0, 10);
//        Page<QuestionAnswer> all = questionAnswerRepository.findAll(pageable);
        Page<QuestionAnswer> all = questionAnswerRepository.searchQuestionAnswer(null, pageable);
        //when


        List<QuestionAnswer> content = all.getContent();

        content.forEach(questionAnswer -> {
            log.info(questionAnswer + "");
            log.info(questionAnswer.getStudent() + "");
        });


    }

    @Test
    public void 레슨인덱스학생이름으로검색() throws Exception {
        //given

        Pageable pageable = PageRequest.of(0, 10);
        Page<QuestionAnswer> findBy = questionAnswerRepository.searchQuestionAnswer("새별", pageable);

        List<QuestionAnswer> content = findBy.getContent();

        content.forEach(questionAnswer -> {
            log.info("questionAnswer(새별) {}", questionAnswer);
        });


        //when

        //then

    }
    
    @Test
    public void 레슨으로조회() throws Exception {

        Optional<Lesson> byId = lessonRepository.findById(1);
        Lesson lesson = byId.orElseThrow();

        log.info("lesson : {}", lesson);
        //given
        List<QuestionAnswer> result = questionAnswerRepository.findByLesson(lesson);

        //when
        
        //then
        result.forEach(questionAnswer ->  {
            log.info(questionAnswer + "");
        });
    
    }

    

}
