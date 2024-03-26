package com.ezen.management.repository.search;

import com.ezen.management.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberSearch {

    Page<Member> searchAdmin(Pageable pageable);

}
