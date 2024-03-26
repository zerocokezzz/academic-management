package com.ezen.management.repository;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    public void insertMaster(){
        Member member = Member.builder()
                .id("master")
                .pwd(passwordEncoder.encode("1111"))
                .name("관리자")
                .build();

        member.addRole(MemberRole.MASTER);

        memberRepository.save(member);
    }

    @Test
    public void insertSecretMaster(){
        Member member = Member.builder()
                .id("rnaster")
                .pwd(passwordEncoder.encode("1111"))
                .name("관리자")
                .build();

        member.addRole(MemberRole.MASTER);

        memberRepository.save(member);
    }

    @Test
    public void insertAdmin(){

        Member member = Member.builder()
                .id("admin")
                .pwd(passwordEncoder.encode("1111"))
                .name("행정")
                .build();

        member.addRole(MemberRole.ADMIN);

        memberRepository.save(member);
    }

    @Test
    public void selectOne(){
        Optional<Member> result = memberRepository.getByIdWithRoles("master");
        Member member = result.orElseThrow();

        log.info("member...... " + member);

        member.getRoleSet().forEach(role -> {
            log.info("member role " + role);
        });


    }

    @Test
    public void insertTeacher(){
        Member member = Member.builder()
                .id("teacher2")
                .pwd(passwordEncoder.encode("1111"))
                .name("강사")
                .build();

        member.addRole(MemberRole.TEACHER);

        memberRepository.save(member);
    }

    @Test
    public void updateTeacher(){
        Optional<Member> result = memberRepository.getByIdWithRoles("teacher");
        Member member = result.orElseThrow();

        member.changeName("강사");
        memberRepository.save(member);

    }

    @Test
    public void 전체목록(){
//        List<Member> all = memberRepository.findAll();
        Optional<List<Member>> allByWithRoles = memberRepository.getAllByWithRoles();
        List<Member> all = allByWithRoles.orElseThrow();
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






}
