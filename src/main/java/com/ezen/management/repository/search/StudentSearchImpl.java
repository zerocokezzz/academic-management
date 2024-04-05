package com.ezen.management.repository.search;

import com.ezen.management.domain.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

public class StudentSearchImpl extends QuerydslRepositorySupport implements StudentSearch {

    public StudentSearchImpl() {
        super(Student.class);
    }

    @Override
    public Page<Student> searchStudent(Long lessonIdx, String[] types, String keyword, Pageable pageable) {
        QStudent student = QStudent.student;

        JPQLQuery<Student> query = from(student);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(lessonIdx != null){
            booleanBuilder.and(student.lesson.idx.eq(lessonIdx));
        }

        if (types != null && keyword != null) {
            for (String type : types) {
                switch (type) {
                    case "n": //학생이름
                        booleanBuilder.and(student.name.contains(keyword));
                        break;
                }
            }
        }

        query.where(booleanBuilder);

        //페이징
        this.getQuerydsl().applyPagination(pageable, query);

        List<Student> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
