package com.ezen.management.repository;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import com.ezen.management.repository.search.MemberSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<Member, String>, MemberSearch {

//    아이디로 조회(권한 포함)
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.id = :id")
    Optional<Member> getByIdWithRoles(String id);

//    멤버 전체(권한 포함)
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m")
    Page<Member>  getAllByWithRoles(Pageable pageable);
//    Optional<List<Member>> getAllByWithRoles();

//    특정 권한을 가진 멤버
    @EntityGraph(attributePaths = "roleSet")
    @Query( "select m from Member m left join m.roleSet r where r in :roles")
    Page<Member> findBySpecificRoles(Set<MemberRole> roles, Pageable pageable);

//    총 멤버 수
    @Query("select count(m) from Member m")
    int countAll();

//    특정 권한을 가진 멤버 수
    @Query( "select count(m) from Member m left join m.roleSet r where r in :roles")
    int countBySpecificRoles(Set<MemberRole> roles);

//    특정 권한을 가지고 있고, 아이디가 keyword에 해당하는 멤버
    @Query("select m from Member m left join m.roleSet r where m.id like concat('%', :keyword, '%') and r in :roles")
    Page<Member> findByKeywordAndSpecificRoles(String keyword, Pageable pageable, Set<MemberRole> roles);



}
