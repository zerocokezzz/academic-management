package com.ezen.management.service;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Student;
import com.ezen.management.domain.Subject;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.repository.LessonRepository;
import com.ezen.management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public Student findByLessonIdxAndName(Long lessonIdx, String name) {
        Optional<Lesson> result = lessonRepository.findById(lessonIdx);
        Lesson lesson = result.orElseThrow();

        Optional<Student> byLessonAndName = studentRepository.getByLessonAndName(lesson, name);

//        null 처리 해야함
        return byLessonAndName.get();

    }

    @Override
    public Student findById(Long studentIdx) {
        Optional<Student> byId = studentRepository.findById(studentIdx);
        Student student = byId.get();

        log.info("student : {}", student);


//        null 처리 해야함!
        return byId.get();
    }

    @Override
    public PageResponseDTO<Student> searchStudent(Long lessonIdx, PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable();

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Page<Student> studentPage = studentRepository.searchStudent(lessonIdx, types, keyword, pageable);

        List<Student> dtoList = studentPage.getContent();


//        return new PageImpl<Student>(dtoList, pageable, studentPage.getTotalElements());

        return PageResponseDTO.<Student>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)studentPage.getTotalElements())
                .build();

    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }


}
