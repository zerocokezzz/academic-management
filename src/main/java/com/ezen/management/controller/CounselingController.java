package com.ezen.management.controller;

import com.ezen.management.domain.Counseling;
import com.ezen.management.domain.Student;
import com.ezen.management.dto.*;
import com.ezen.management.service.CounselingService;
import com.ezen.management.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/counseling")
public class CounselingController {

    @Autowired
    private final CounselingService counselingService;
    @Autowired
    private final StudentService studentService;


    public CounselingController(CounselingService counselingService, StudentService studentService) {
        this.counselingService = counselingService;
        this.studentService = studentService;
    }



    //전체목록
    @GetMapping("/list")
    public void counselingList(PageRequestDTO pageRequestDTO
            , Model model){

        //상담목록
        PageResponseDTO<Counseling> responseDTO = counselingService.counselingList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        log.info("responseDTO= " + responseDTO);

    }


    //학생 상세조회
    @GetMapping({"/detail","/update"})
    public void detail(Long idx, Student student, Model model, PageRequestDTO pageRequestDTO){

        log.info("------가자가자가자 상세페이지------");

       CounselingStudentDTO counselingStudentDTO = counselingService.detail(idx);
        log.info("counselingStudentDTO= " + counselingStudentDTO);
        model.addAttribute("dto", counselingStudentDTO);
    }


    @GetMapping("/counselingDetail")
    public void findByStudentIdx(Model model, @RequestParam("idx")Long studentIdx) {
        //왜 스트링을 보낼때는 안가고 void로 하니까 갈까?

        //학생 단일 정보도 보내기
        Student student = studentService.findById(studentIdx);
        model.addAttribute("student", student);
        log.info("student= " + student);


        //학생정보 List에 실어보내기
        List<Counseling> counselingList = counselingService.findByStudentIdx(studentIdx);
        model.addAttribute("counselingList", counselingList);
        log.info("counselingList= " + counselingList);

    }



    //추가하기
    @GetMapping("/insert")
    public void insert(Model model, CounselingDTO counselingDTO, @RequestParam("idx") Long studentIdx){

        log.info("안녕 여기 추가화면 가는 중");
        //학생 단일 정보 보내기
        Student student = studentService.findById(studentIdx);
        model.addAttribute("student", student);
        log.info("student= " + student);

    }
    @PostMapping("/insert")
    public String insert(CounselingDTO counselingDTO

                            ,BindingResult bindingResult
                            , RedirectAttributes redirectAttributes){

        log.info("안녕 여기 추가화면 나오는 중");

        log.info("counselingDTO= " + counselingDTO);

        if(bindingResult.hasErrors()){
            log.info("-----has errors-----");
            redirectAttributes.addFlashAttribute("errors", bindingResult.hasErrors());

            redirectAttributes.addAttribute("idx", counselingDTO.getStudentIdx());

//            return "redirect:/counseling/insert?idx=" + counselingDTO.getStudentIdx();
            return "redirect:/counseling/insert";
        }


        Long idx = counselingService.insert(counselingDTO);
        redirectAttributes.addFlashAttribute("result", idx);

        redirectAttributes.addAttribute("idx", counselingDTO.getStudentIdx());

        return "redirect:/counseling/counselingDetail";

    }



    //수정하기
    @PostMapping("/update")
    public String update(CounselingStudentDTO counselingStudentDTO
                         , PageRequestDTO pageRequestDTO
                         , BindingResult bindingResult
                         , RedirectAttributes redirectAttributes){

        log.info("counseling UpdateAction gogo");

        if (bindingResult.hasErrors()){
            log.info("has errors...");

            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("idx", counselingStudentDTO.getCounselingIdx());

        //오류 발생시 다시 update로 이동시켜
            return "redirect:/counseling/update?" + link;
        }

        counselingService.update(counselingStudentDTO);
        redirectAttributes.addFlashAttribute("result", "updated");
        redirectAttributes.addAttribute("idx", counselingStudentDTO.getCounselingIdx());

        log.info("counselingStudentDTO= " + counselingStudentDTO);

        return "redirect:/counseling/detail";
    }



    //삭제하기
    @PostMapping("/delete")
    public String delete(Long idx, RedirectAttributes redirectAttributes){

        log.info("counseling delete gogo" + idx);

        counselingService.delete(idx);
        redirectAttributes.addFlashAttribute("result", "delete");

        return "redirect:/counseling/list";

    }



// 한개의 상담을 단일적으로 가져옴
//    @GetMapping("/counselingDetail")
//    public String counselingDetail(Model model
//                                    ,@RequestParam("idx")Long idx
//                                    ,RedirectAttributes redirectAttributes){
//
//        log.info("안녕 여기 상담자세히");
//
//       //값이 null인지 아닌지부터 확인
//        if (idx == null) {
//            redirectAttributes.addFlashAttribute("errors", "idx값이 null null 하네요"); //에러 메시지 반환
//            log.info("idx null null");
//            return "redirect:/lesson";
//
//        }
//
//        //학생 정보가 없다면
//        Student student = studentService.findById(idx);
//
//        if(student == null){
//            redirectAttributes.addFlashAttribute("errors", "학생 정보를 찾을 수 없습니다.");
//            log.info("student null null");
//            return "redirect:/lesson";
//
//        }
//
//        // 학생의 counseling 횟수가 0보다 크거나 같으면서 counseling 정보도 있는 경우
//
//        // 학생 정보가 있는 상태에서 예약 정보가 없는 경우
//        Counseling counseling = counselingService.findById(idx);
//
//        // counseling 정보가 없는 경우에도 계속 진행
//        if (counseling == null) {
//            log.info("counseling null null");
//            log.info("studentIdx= " + student.getIdx());
//            return "/counseling/counselingDetail";
//
//        } else {
//            // 상담 정보 찾기 위한 학생
//            model.addAttribute("counseling", counseling);
//            log.info("counseling= " + counseling);
//
//            // 학생정보로 수업 찾아오기
//            model.addAttribute("student", student);
//            log.info("student= " + student);
//
//            redirectAttributes.addAttribute("idx", student.getIdx());
//            log.info("idx=" + idx);
//
//            return "/counseling/counselingDetail";
//        }
//    }






}
