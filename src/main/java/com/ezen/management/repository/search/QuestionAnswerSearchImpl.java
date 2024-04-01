package com.ezen.management.repository.search;


import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.QQuestionAnswer;
import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.service.QuestionAnswerService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class QuestionAnswerSearchImpl extends QuerydslRepositorySupport implements QuestionAnswerSearch {

    public QuestionAnswerSearchImpl() {
        super(QuestionAnswer.class);
    }

    @Override
    public Page<QuestionAnswer> searchQuestionAnswer(String keyword, Pageable pageable) {

        QQuestionAnswer questionAnswer = QQuestionAnswer.questionAnswer;

//        select ... from question_answer
        JPQLQuery<QuestionAnswer> query = from(questionAnswer);

//        order by idx
        query.orderBy(questionAnswer.idx.desc());

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(keyword != null){
            booleanBuilder.or(questionAnswer.student.name.like(keyword));
        }

//        if(lessonIdx != 0){
//            booleanBuilder.and(questionAnswer.student.lesson.idx.eq(lessonIdx));
//        }

        query.where(booleanBuilder);

        this.getQuerydsl().applyPagination(pageable, query);

        List<QuestionAnswer> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }
}
