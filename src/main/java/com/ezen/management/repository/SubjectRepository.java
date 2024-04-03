package com.ezen.management.repository;

import com.ezen.management.domain.Subject;
import com.ezen.management.repository.search.SubjectSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long>, SubjectSearch {
}
