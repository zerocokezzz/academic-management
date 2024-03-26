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

import java.util.Arrays;
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
        Pageable pageable = pageRequestDTO.getPageable();

        String[] types = pageRequestDTO.getTypes(); // split("_")
        String keyword = pageRequestDTO.getKeyword();

        log.info("types: " + Arrays.toString(types));
        log.info("keyword: " + keyword);

//        검색 카테고리, 키워드, 특정 권한을 가진 멤버 regDate 내림차순으로 페이징 처리해서 가져옴
        Page<Member> memberPage = memberRepository.searchMember(types, keyword, pageable, memberRoleSet);

//        페이지 안에 담긴 dtoList
        List<Member> dtoList = memberPage.getContent();

        return PageResponseDTO.<Member>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
//                검색했을 때 총 개수(페이지에 담긴 개수 말고 검색 결과 개수)
                .total((int)memberPage.getTotalElements())
                .build();
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

    @Override
    public int countAll() {
        return memberRepository.countAll();
    }


}
