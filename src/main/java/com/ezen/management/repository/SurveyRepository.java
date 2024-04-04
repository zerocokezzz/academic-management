package com.ezen.management.repository;

import com.ezen.management.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    List<Survey> findAllByRound(int round);

    @Transactional
    int deleteAllByRound(int round);
}
