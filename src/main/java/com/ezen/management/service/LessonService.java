package com.ezen.management.service;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Student;
import com.ezen.management.domain.SubjectTest;
import com.ezen.management.dto.LessonPageRequestDTO;
import com.ezen.management.dto.LessonPageResponseDTO;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {

    List<Lesson> findAll();

    List<Lesson> findAllGreaterThan(LocalDate day);

    Lesson findById(Long lessonIdx);

    //진행중인 수업 & 검색 & 페이징
    public PageResponseDTO<Lesson> ongoingLesson(PageRequestDTO pageRequestDTO);

    List<Lesson> getOngoingLesson();

    //완료된 수업 & 검색 & 페이징
    public PageResponseDTO<Lesson> endLesson(PageRequestDTO pageRequestDTO);


    PageResponseDTO<Student> searchStudent(PageRequestDTO pageRequestDTO, Long lessonIdx);

    List<SubjectTest> searchSubjectTest(Long studentIdx);



}
