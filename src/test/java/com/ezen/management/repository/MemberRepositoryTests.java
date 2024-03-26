package com.ezen.management.repository;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@Slf4j
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void 관리자추가(){
        Member member = Member.builder()
                .id("master")
                .pwd(passwordEncoder.encode("1111"))
                .name("관리자")
                .build();

        member.addRole(MemberRole.MASTER);

        memberRepository.save(member);
    }

    @Test
    public void 비밀관리자추가(){
        Member member = Member.builder()
                .id("rnaster")
                .pwd(passwordEncoder.encode("1111"))
                .name("관리자")
                .build();

        member.addRole(MemberRole.MASTER);

        memberRepository.save(member);
    }

    @Test
    public void 행정등록(){

        Member member = Member.builder()
                .id("admin")
                .pwd(passwordEncoder.encode("1111"))
                .name("행정")
                .build();

        member.addRole(MemberRole.ADMIN);

        memberRepository.save(member);
    }

    @Test
    public void 교사한명(){
        Optional<Member> result = memberRepository.getByIdWithRoles("master");
        Member member = result.orElseThrow();

        log.info("member...... " + member);

        member.getRoleSet().forEach(role -> {
            log.info("member role " + role);
        });


    }

    @Test
    public void 교사등록(){
        Member member = Member.builder()
                .id("teacher")
                .pwd(passwordEncoder.encode("1111"))
                .name("강사")
                .build();

        member.addRole(MemberRole.TEACHER);

        memberRepository.save(member);
    }

    @Test
    public void 교사수정(){
        Optional<Member> result = memberRepository.getByIdWithRoles("teacher");
        Member member = result.orElseThrow();

        member.changeName("강사");
        memberRepository.save(member);

    }

    @Test
    public void 전체목록(){
//        List<Member> all = memberRepository.findAll();

//        시작, 개수 가지고 들어와야함

//        (0, 10) 1페이지, 10명까지만 잘라서
        Pageable pageable = PageRequest.of(0, 10);
        Page<Member> allByWithRoles = memberRepository.getAllByWithRoles(pageable);
        List<Member> all = allByWithRoles.getContent();
        log.info("member list...... " + all);

        all.forEach(member -> {
            Set<MemberRole> roleSet = member.getRoleSet();
            log.info("권한 " + roleSet);
        });

    }

    @Test
    public void 강사삭제(){
        Optional<Member> teacher3 = memberRepository.findById("teacher2");
        Member member = teacher3.orElseThrow();

        memberRepository.delete(member);
    }

    @Test
    public void 페이징(){

//        page는 0부터 시작함 of(0, 10) 1페이지, 10
        Pageable pageable = PageRequest.of(0, 10);
        Page<Member> result = memberRepository.findAll(pageable);

        log.info("result...... " + result);
        result.forEach(member -> {
            log.info("member...... " + member);
        });
    }

    @Test
    public void 전체회원수(){

        int all = memberRepository.countAll();
        log.info("count......" + all);
    }

    @Test
    public void 특정권한조회(){

        Set<MemberRole> memberRoleSet = new HashSet<>();

        memberRoleSet.add(MemberRole.ADMIN);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Member> bySpecificRoles = memberRepository.findBySpecificRoles(memberRoleSet, pageable);
        log.info("byroles " + bySpecificRoles);

        List<Member> content = bySpecificRoles.getContent();

        content.forEach(member -> {
            log.info("memberRole " + member.getRoleSet());
        });
    }

    @Test
    public void 특정권한인원조회(){
        Set<MemberRole> set = new HashSet<>();
        set.add(MemberRole.ADMIN);
        int totalAdmin = memberRepository.countBySpecificRoles(set);

        log.info("ADMIN은 총 " + totalAdmin + "명입니다.");
    }

    @Test
    public void 행정대량입력(){

        for(int i = 1; i <= 50; i++){
            Member member = Member.builder()
                    .id("admin2222" + i)
                    .pwd(passwordEncoder.encode("1111"))
                    .name("행정2222" + i)
                    .build();

            member.addRole(MemberRole.ADMIN);

            memberRepository.save(member);
        }

    }





}
