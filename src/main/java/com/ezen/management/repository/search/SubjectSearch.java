package com.ezen.management.repository.search;

import com.ezen.management.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectSearch {

    Page<Subject> searchSubject(String[] types, String keyword, Pageable pageable);
}
