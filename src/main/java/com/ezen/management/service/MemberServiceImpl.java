package com.ezen.management.service;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import com.ezen.management.domain.QMember;
import com.ezen.management.dto.MemberDTO;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public PageResponseDTO<Member> findAll(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("regDate");
//        String keyword = pageRequestDTO.getKeyword();

//        List<Member> dtoList = memberRepository.findAll();
        Page<Member> all = memberRepository.getAllByWithRoles(pageable);

        List<Member> dtoList = all.getContent();

        log.info("memberServiceImpl dtoList " + dtoList);
        dtoList.forEach(member -> {
            log.info("member roleSet" + member.getRoleSet());
        });

        return PageResponseDTO.<Member>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(memberRepository.countAll())
                .build();

    }

    @Override
    public PageResponseDTO<Member> findBySpecificRoles(Set<MemberRole> memberRoleSet, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("regDate");

        Page<Member> bySpecificRoles = memberRepository.findBySpecificRoles(memberRoleSet, pageable);

        List<Member> dtoList = bySpecificRoles.getContent();

        return PageResponseDTO.<Member>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(memberRepository.countBySpecificRoles(memberRoleSet))
                .build();
    }

//    @Override
//    public List<Member> findAll(Pageable pageable) {
////        Optional<List<Member>> allByWithRoles = memberRepository.getAllByWithRoles();
////        return allByWithRoles.orElse(null);
//
////        여기에 페이지
////        Pageable pageable = PageRequest.of(0, 10);
//        Page<Member> page = memberRepository.getAllByWithRoles(pageable);
//
//
//        return page.getContent();   //  List<Member>
//    }



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

    @Override
    public int countAll() {
        return memberRepository.countAll();
    }


}
