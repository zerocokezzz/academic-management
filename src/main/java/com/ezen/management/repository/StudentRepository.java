package com.ezen.management.repository;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Student;
import com.ezen.management.repository.search.StudentSearch;
import com.ezen.management.service.LessonService;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> , StudentSearch {

    @EntityGraph(attributePaths = "lesson")
    @Query("select s from Student s where s.lesson = :lesson and s.name = :name")
    Optional<Student> getByLessonAndName(Lesson lesson, String name);

    @Query("select s from Student s where s.lesson.idx = :lessonIdx ")
    Optional<Student> getByLessonIdx(long lessonIdx);

}
