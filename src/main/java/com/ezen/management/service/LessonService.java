package com.ezen.management.service;

import com.ezen.management.domain.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {

    List<Lesson> findAll();

    List<Lesson> findAllGreaterThan(LocalDate day);

    Lesson findById(int lessonIdx);

}
