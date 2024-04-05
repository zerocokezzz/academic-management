package com.ezen.management.service;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Student;
import com.ezen.management.dto.LessonPageRequestDTO;
import com.ezen.management.dto.LessonPageResponseDTO;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.repository.LessonRepository;
import com.ezen.management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService{

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;

    public List<Lesson> findAll(){
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> findAllGreaterThan(LocalDate day) {
        return lessonRepository.getLessonsByEndDayGreaterThan(day);

    }

    @Override
    public Lesson findById(Long lessonIdx) {
        Optional<Lesson> byId = lessonRepository.findById(lessonIdx);

        return byId.orElse(null);
    }

    //진행중인 수업 & 검색 & 페이징
    @Override
    public PageResponseDTO<Lesson> ongoingLesson(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable();

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Page<Lesson> lessonPage = lessonRepository.searchLessonOngoing(types,keyword,pageable);

        List<Lesson> dtoList = lessonPage.getContent();

        return PageResponseDTO.<Lesson>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)lessonPage.getTotalElements())
                .build();
    }

    //완료된 수업 & 검색 & 페이징
    @Override
    public PageResponseDTO<Lesson> endLesson(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable();

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Page<Lesson> lessonPage = lessonRepository.searchLessonEnd(types, keyword, pageable);

        List<Lesson> dtoList = lessonPage.getContent();

        return PageResponseDTO.<Lesson>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)lessonPage.getTotalElements())
                .build();
    }

    //학생 목록
    @Override
    public PageResponseDTO<Student> searchStudent(PageRequestDTO pageRequestDTO, Long lessonIdx){
        Pageable pageable = pageRequestDTO.getPageable();

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Page<Student> studentPage = studentRepository.searchStudent(lessonIdx, types, keyword, pageable);
        List<Student> dtoList = studentPage.getContent();

        return PageResponseDTO.<Student>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)studentPage.getTotalElements())
                .build();
    }

}
