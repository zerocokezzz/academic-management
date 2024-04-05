package com.ezen.management.repository;


import com.ezen.management.domain.Question;
import com.ezen.management.repository.search.QuestionSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionSearch {

    List<Question> getQuestionsByName(String questionName);

//    List<Question> getQuestionsByKeyword(Pageable pageable);

}
