package com.ezen.management.service;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import com.ezen.management.dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    void adminInsert(MemberDTO memberDTO);

    void teacherInsert(MemberDTO memberDTO);

    List<Member> findAll();

    int delete(String id);

    int update(MemberDTO memberDTO);

    Optional<Member> findById(String id);

}
