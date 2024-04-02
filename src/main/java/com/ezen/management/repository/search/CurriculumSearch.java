package com.ezen.management.repository.search;

import com.ezen.management.domain.Curriculum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurriculumSearch {
    Page<Curriculum> searchCurriculum(String[] types, String keyword, Pageable pageable);
}
