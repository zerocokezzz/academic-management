package com.ezen.management.repository.search;

import com.ezen.management.domain.Counseling;
import com.ezen.management.domain.QCounseling;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class CounselingSearchImpl extends QuerydslRepositorySupport implements CounselingSearch {


    public CounselingSearchImpl() {
        super(Counseling.class);
    }


    @Override
    public Page<Counseling> searchAll(String[] types, String keyword, Pageable pageable) {

        QCounseling counseling = QCounseling.counseling;
        JPQLQuery<Counseling> query = from(counseling);

        if((types != null && types.length > 0) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String type: types){

                switch (type){
                    case "t": //날짜
                        //날짜 타입은 이걸로 검색도 가능?
//                        LocalDateTime startDate = LocalDateTime.parse(keyword + "T00:00:00");
//                        LocalDateTime endDate = LocalDateTime.parse(keyword + "T23:59:59");
//                        booleanBuilder.or(counseling.counselingDate.between(startDate, endDate));

                        //문자열 타입으로 변환해서 검색
                        booleanBuilder.or(counseling.counselingDate.stringValue().contains(keyword));
                        break;
                    case "c": //상담방식
                        booleanBuilder.or(counseling.method.stringValue().contains(keyword));
                        break;
                    case "w": //담당자
                        booleanBuilder.or(counseling.writer.contains(keyword));
                        break;
                    case "n": //학생이름
                        booleanBuilder.or(counseling.student.name.contains(keyword));
                        break;

                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        query.where(counseling.idx.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Counseling> list = query.fetch();

        Long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
