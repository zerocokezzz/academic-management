package com.ezen.management.service;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Student;
import com.ezen.management.repository.LessonRepository;
import com.ezen.management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public Student findByLessonIdxAndName(int lessonIdx, String name) {
        Optional<Lesson> result = lessonRepository.findById(lessonIdx);
        Lesson lesson = result.orElseThrow();

        Optional<Student> byLessonAndName = studentRepository.getByLessonAndName(lesson, name);

        return byLessonAndName.orElseThrow();

    }
}
