package com.ezen.management.repository;

import com.ezen.management.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMaster(){
        Member member = Member.builder()
                .id("master")
                .pwd("1111")
                .access(0)
                .name("관리자")
                .build();

        memberRepository.save(member);
    }
}
