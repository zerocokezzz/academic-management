package com.ezen.management.repository;

import com.ezen.management.domain.Category;
import com.ezen.management.domain.SubjectTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectTestRepository extends JpaRepository<SubjectTest, Long> {

    @Query("select s from SubjectTest s where s.student.idx = :studentIdx")
    List<SubjectTest> searchSubjectTest(Long studentIdx);
}
