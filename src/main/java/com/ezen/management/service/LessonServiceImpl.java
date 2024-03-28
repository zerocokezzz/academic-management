package com.ezen.management.service;

import com.ezen.management.domain.Lesson;
import com.ezen.management.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService{

    private final LessonRepository lessonRepository;

    public List<Lesson> findAll(){
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> findAllGreaterThan(LocalDate day) {
        return lessonRepository.getLessonsByEndDayGreaterThan(day);

    }
}
