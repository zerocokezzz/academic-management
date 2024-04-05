package com.ezen.management.repository;

import com.ezen.management.domain.SubjectHold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectHoldRepository extends JpaRepository<SubjectHold, Long> {

    @Query("select s.name from SubjectHold s where  s.lesson_idx= :lessonIdx")
    List<String> getSubjectHoldByLesson_idx(Long lessonIdx);

}
