package com.ezen.management.repository;

import com.ezen.management.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {


}
