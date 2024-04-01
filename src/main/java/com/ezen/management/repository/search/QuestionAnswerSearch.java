package com.ezen.management.repository.search;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.QuestionAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionAnswerSearch {

    Page<QuestionAnswer> searchQuestionAnswer(String keyword, Pageable pageable);
}
