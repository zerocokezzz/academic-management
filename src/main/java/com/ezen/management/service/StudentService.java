package com.ezen.management.service;

import com.ezen.management.domain.Student;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student findByLessonIdxAndName(Long lessonIdx, String name);

    Student findById(Long studentIdx);

    List<Student> findAll();

    PageResponseDTO<Student> searchStudent(Long lessonIdx, PageRequestDTO pageRequestDTO);

    void insertStudent(StudentDTO studentDTO);

    void modifyStudent(StudentDTO studentDTO);

    void deleteStudent(StudentDTO studentDTO);

}
