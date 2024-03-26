package com.ezen.management.service;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import com.ezen.management.domain.QMember;
import com.ezen.management.dto.MemberDTO;
import com.ezen.management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void adminInsert(MemberDTO memberDTO) {

        Member member = Member.builder()
                .id(memberDTO.getId())
                .pwd(passwordEncoder.encode(memberDTO.getPwd()))
                .name(memberDTO.getName())
                .build();

        if(!memberDTO.getFileName().isEmpty()){
            member.changeProfile(memberDTO.getFileName());
        }

        member.addRole(MemberRole.ADMIN);

        memberRepository.save(member);

    }

    @Override
    public void teacherInsert(MemberDTO memberDTO) {

        Member member = Member.builder()
                .id(memberDTO.getId())
                .pwd(passwordEncoder.encode(memberDTO.getPwd()))
                .name(memberDTO.getName())
                .build();

        if(!memberDTO.getFileName().isEmpty()){
            member.changeProfile(memberDTO.getFileName());
        }

        member.addRole(MemberRole.TEACHER);

        memberRepository.save(member);

    }

    @Override
    public List<Member> findAll() {
//        return memberRepository.findAll();
        Optional<List<Member>> allByWithRoles = memberRepository.getAllByWithRoles();

        return allByWithRoles.orElse(null);

    }

    @Override
    public int delete(String id) {
        log.info("id......" + id);

        Optional<Member> result = memberRepository.findById(id);

        log.info("findById result......" + result);

        if(result.isPresent()){
            Member member = result.get();

            log.info("result.get()......" + member);
            memberRepository.delete(member);

            return 1;
        }

        return 0;

    }

    @Override
    public int update(MemberDTO memberDTO) {

        Optional<Member> result = memberRepository.findById(memberDTO.getId());

        if(result.isPresent()){
            Member member = result.get();

//            프로필 사진, 이름만 변경 가능
//            비밀번호 변경은 로직 따로
            member.changeName(memberDTO.getName());

            if(!memberDTO.getFileName().isEmpty()){
                member.changeProfile(memberDTO.getFileName());
            }

            log.info("member......" + member);
            memberRepository.save(member);

            return 1;
        }
        return 0;
    }

    @Override
    public Optional<Member> findById(String id) {

        return memberRepository.findById(id);
    }


}
