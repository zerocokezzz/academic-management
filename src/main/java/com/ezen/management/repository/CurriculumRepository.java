package com.ezen.management.repository;

import com.ezen.management.domain.Curriculum;
import com.ezen.management.repository.search.CurriculumSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> , CurriculumSearch {



}
