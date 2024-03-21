package com.ezen.management.repository;

import com.ezen.management.domain.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {
}
