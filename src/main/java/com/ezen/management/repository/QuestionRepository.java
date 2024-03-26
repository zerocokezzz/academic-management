package com.ezen.management.repository;


import com.ezen.management.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Optional<List<Question>> getQuestionsByName(String name);

}
