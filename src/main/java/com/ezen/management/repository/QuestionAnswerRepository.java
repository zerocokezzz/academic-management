package com.ezen.management.repository;


import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.repository.search.QuestionAnswerSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long>, QuestionAnswerSearch {

    @Query("select qa from QuestionAnswer qa")
    Page<QuestionAnswer> findAll(Pageable pageable);

    List<QuestionAnswer> findByName(String name);

//    lesson 회차 마다 불러와야함
//    questionAnswer는 학생을 가지고 있음
//    학생은 lesson을 가지고 있음 -> lesson.getNumber()는 회차를 의미함
    @Query("select qa from QuestionAnswer qa left join Student s on qa.student.idx = s.idx where s.lesson = :lesson")
    List<QuestionAnswer> findByLesson(Lesson lesson);

    @Query("select qa from QuestionAnswer qa where qa.student.idx = :studentIdx")
    QuestionAnswer findByStudentIdx(Long studentIdx);


}
