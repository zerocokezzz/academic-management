package com.ezen.management.repository;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @EntityGraph(attributePaths = "lesson")
    @Query("select s from Student s where s.lesson = :lesson and s.name = :name")
    Optional<Student> getByLessonAndName(Lesson lesson, String name);

//    @Transactional
//    @Modifying
//    @Query("UPDATE Student s SET s.survey1 = :survey1 WHERE s.idx = :studentIdx")
//    int updateSurvey1ById(@Param("studentIdx") Long studentIdx, @Param("survey1") boolean survey1);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE Student s SET s.survey2 = :survey2 WHERE s.idx = :studentIdx")
//    int updateSurvey2ById(@Param("studentIdx") Long studentIdx, @Param("survey2") boolean survey2);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE Student s SET s.survey3 = :survey3 WHERE s.idx = :studentIdx")
//    int updateSurvey3ById(@Param("studentIdx") Long studentIdx, @Param("survey3") boolean survey3);
}
