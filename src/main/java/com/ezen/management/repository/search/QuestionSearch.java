package com.ezen.management.repository.search;

import com.ezen.management.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionSearch {

    Page<Question> searchQuestion(String[] types, String keyword, Pageable pageable);
}
