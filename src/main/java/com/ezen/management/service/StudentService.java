package com.ezen.management.service;

import com.ezen.management.domain.Student;
import com.ezen.management.dto.StudentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface StudentService {

    Student findByLessonIdxAndName(Long lessonIdx, String name);

    Student findById(Long studentIdx);

}
