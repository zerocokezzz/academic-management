package com.ezen.management.controller;

import com.ezen.management.domain.Member;
import com.ezen.management.domain.MemberRole;
import com.ezen.management.dto.MemberDTO;
import com.ezen.management.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

//    행정 관리 홈(행정 리스트)
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/admin")   //  /member/admin
    public String adminIndex(Model model){

        List<Member> memberList = memberService.findAll();

        model.addAttribute("memberList", memberList);
        model.addAttribute("admin", MemberRole.ADMIN);

        return "/member/admin/index";
    }

//    행정 추가 폼
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/admin/insert")    //  /member/admin/insert GET
    public String adminInsertGET(){
        log.info("----------------- ADMIN insert GET -----------------");
        return "/member/admin/insert";
    }

//    행정 추가
    @PreAuthorize("hasRole('MASTER')")
    @PostMapping("/admin/insert")   //  /member/admin/insert POST
    public String adminInsertPOST(MemberDTO memberDTO){
        log.info("----------------- ADMIN insert POST -----------------");

        memberService.adminInsert(memberDTO);
        log.info("memberDTO......" + memberDTO);

        return "redirect:/member/admin";
    }


//    행정 수정 폼
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/admin/update")
    public String adminUpdateGET(String id, Model model){

        Optional<Member> result = memberService.findById(id);

        if(result.isPresent()){
            Member admin = result.get();
            model.addAttribute("admin", admin);
            return "/member/admin/update";
        }

        return "redirect:/member/admin";

    }


//    행정 수정
    @PreAuthorize("hasRole('MASTER')")
    @PostMapping("/admin/update")
    public String adminUpdatePost(MemberDTO memberDTO, HttpServletResponse response){

//        수정 로직은 리턴 뷰 빼고 행정/강사가 똑같음 리팩토링할 방법 찾아볼 것
        log.info("admin post update memberDTO....." + memberDTO);

        int result = memberService.update(memberDTO);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter w = null;

        try {
            w = response.getWriter();
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return "redirect:/member/admin";
        }

//        수정 성공
        if(result == 1){
            w.println("<script> alert('수정되었습니다.');");
        }else{
            w.println("<script> alert('error!');");
        }

        w.println("location.href='/member/admin' </script>");
        w.close();

        return null;

    }


    //    행정 삭제
    @PreAuthorize("hasRole('MASTER')")
    @GetMapping("/admin/delete")
    public String adminDelete(String id, HttpServletResponse response){

//        삭제 로직 행정/강사 똑같음 리턴 뷰만 빼면
//        일단 따로 구현은 했는데 메서드 하나로 가능할 것 같음

        log.info("id......" + id);

        int result = memberService.delete(id);

        log.info("deleteById result......" + result);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter w = null;

        try {
            w = response.getWriter();
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return "redirect:/member/teacher";
        }

//        삭제 성공
        if(result == 1){
            w.println("<script> alert('삭제되었습니다.');");
        }else{
            w.println("<script> alert('error!');");
        }

        w.println("location.href='/member/teacher' </script>");
        w.close();

        return null;
    }



//    교사 관리 홈(교사 리스트)
    @PreAuthorize("hasAnyRole('MASTER', 'ADMIN')")
    @GetMapping("/teacher")
    public String teacherIndex(Model model){

        List<Member> memberList = memberService.findAll();

        model.addAttribute("memberList", memberList);
        model.addAttribute("teacher", MemberRole.TEACHER);

        return "/member/teacher/index";
    }

//    교사 추가 폼
    @PreAuthorize("hasAnyRole('MASTER', 'ADMIN')")
    @GetMapping("/teacher/insert")
    public String teacherInsertGET(){
        log.info("----------------- TEACHER insert GET -----------------");
        return "/member/teacher/insert";
    }


//    교사 추가
    @PreAuthorize("hasAnyRole('MASTER', 'ADMIN')")
    @PostMapping("/teacher/insert")   //  /member/admin/insert POST
    public String teacherInsertPOST(MemberDTO memberDTO){
        log.info("----------------- TEACHER insert POST -----------------");

        memberService.teacherInsert(memberDTO);
        log.info("memberDTO......" + memberDTO);

        return "redirect:/member/teacher";
    }


//    교사 수정 폼
//    /teacher 뷰에서 모달로 teacherUpdatePOST 날리면 이건 필요 없음
    @PreAuthorize("hasAnyRole('MASTER', 'ADMIN')")
    @GetMapping("/teacher/update")
    public String teacherUpdateGET(@RequestParam String id, Model model){

        Optional<Member> result = memberService.findById(id);

        if(result.isPresent()){
            Member teacher = result.get();
            model.addAttribute("teacher", teacher);
            return "/member/teacher/update";
        }

        return "redirect:/member/teacher";

    }


//    교사 수정
    @PreAuthorize("hasAnyRole('MASTER', 'ADMIN')")
    @PostMapping("/teacher/update")
    public String teacherUpdatePOST(MemberDTO memberDTO, HttpServletResponse response){

//        수정 로직은 리턴 뷰 빼고 행정/강사가 똑같음 리팩토링할 방법 찾아볼 것
        log.info("post update memberDTO....." + memberDTO);

        int result = memberService.update(memberDTO);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter w = null;

        try {
            w = response.getWriter();
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return "redirect:/member/teacher";
        }

//        수정 성공
        if(result == 1){
            w.println("<script> alert('수정되었습니다.');");
        }else{
            w.println("<script> alert('error!');");
        }

        w.println("location.href='/member/teacher' </script>");
        w.close();

        return null;
    }




//    교사 삭제
    @PreAuthorize("hasAnyRole('MASTER', 'ADMIN')")
    @GetMapping("/teacher/delete")
    public String teacherDelete(@RequestParam String id, HttpServletResponse response){

//        삭제 로직 행정/강사 똑같음 리턴 뷰만 빼면
//        일단 따로 구현은 했는데 메서드 하나로 가능할 것 같음
        log.info("id......" + id);

        int result = memberService.delete(id);

        log.info("deleteById result......" + result);

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter w = null;

        try {
            w = response.getWriter();
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return "redirect:/member/teacher";
        }

//        삭제 성공
        if(result == 1){
            w.println("<script> alert('삭제되었습니다.');");
        }else{
            w.println("<script> alert('error!');");
        }

        w.println("location.href='/member/teacher' </script>");
        w.close();

        return null;
    }



}
