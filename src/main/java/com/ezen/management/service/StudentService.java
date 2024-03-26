package com.ezen.management.service;

import com.ezen.management.domain.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface StudentService {

    Student findByLessonIdxAndName(int lessonIdx, String name);


}
