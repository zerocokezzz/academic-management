package com.ezen.management.repository.search;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface MemberSearch {

    Page<Member> searchMember(String[] types, String keyword, Pageable pageable, Set<MemberRole> memberRoleSet);

}
