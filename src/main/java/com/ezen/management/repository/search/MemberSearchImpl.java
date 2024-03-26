package com.ezen.management.repository.search;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import com.ezen.management.domain.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Set;

public class MemberSearchImpl extends QuerydslRepositorySupport implements MemberSearch {

    public MemberSearchImpl() {
        super(Member.class);
    }

    @Override
    public Page<Member> searchMember(String[] types, String keyword, Pageable pageable, Set<MemberRole> memberRoleSet) {

//        QueryDSL 객체
//        build - java - main - com.ezen.magagement - domain에 있음
        QMember member = QMember.member;

//        select ... from member
        JPQLQuery<Member> query = from(member);

//        order by regDate desc;
        query.orderBy(member.regDate.desc());

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(types != null && keyword != null){

            for(String type : types){
                switch (type){
                    case "i" :
                        //        where id like ...
                        booleanBuilder.or(member.id.contains(keyword));
                        break;
                    case "n" :
                        //        or name like ...
                        booleanBuilder.or(member.name.contains(keyword));
                        break;
                }
            }// for
        }// if

//        and roleSet like
        memberRoleSet.forEach(memberRole -> {
            booleanBuilder.and(member.roleSet.contains(memberRole));
        });

        query.where(booleanBuilder);

//        페이징
        this.getQuerydsl().applyPagination(pageable, query);

        List<Member> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
