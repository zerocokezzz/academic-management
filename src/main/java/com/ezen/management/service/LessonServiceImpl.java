package com.ezen.management.service;

import com.ezen.management.domain.Lesson;
import com.ezen.management.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Lesson findById(int lessonIdx) {
        Optional<Lesson> byId = lessonRepository.findById(lessonIdx);

        return byId.orElse(null);
    }
}
