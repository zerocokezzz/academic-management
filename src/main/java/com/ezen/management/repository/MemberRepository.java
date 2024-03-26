package com.ezen.management.repository;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.id = :id")
    Optional<Member> getByIdWithRoles(String id);

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m")
    Optional<List<Member>>  getAllByWithRoles();


}
