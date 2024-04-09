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

import java.time.LocalDateTime;
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
    @GetMapping("/detail")
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
                            ,RedirectAttributes redirectAttributes){

        log.info("안녕 여기 추가화면 나오는 중");

        //시간 잘 나오나 확인용
        log.info("before counselingDTO= " + counselingDTO);

        //만약 시간이 null 인경우 현재 시간으로 변경하기
        if(counselingDTO.getCounselingDate() == null){
            counselingDTO.setCounselingDate(LocalDateTime.now());
        }

        //시간 변경후 확인용
        log.info("after counselngDTO= " + counselingDTO);


        Long idx = counselingService.insert(counselingDTO);
        log.info("idx= " + idx);

        redirectAttributes.addFlashAttribute("result", idx);
        redirectAttributes.addAttribute("idx", counselingDTO.getStudentIdx());

        return "redirect:/counseling/counselingDetail";
    }


    @GetMapping("/update")
    public void update(Model model
                        ,Student Student
                        ,@RequestParam("idx") Long idx){

        log.info("안녕 여기 수정화면 가는 중");

        //학생 단일 정보 보내기
        Student student = studentService.findById(Student.getIdx());
        model.addAttribute("student", student);
        log.info("student= " + student);

        //상담 정보 보내기
        Counseling counseling = counselingService.findByidx(idx);
        model.addAttribute("counseling", counseling);
        log.info("counseling= " + counseling);

    }

    //수정하기
    @PostMapping("/update")
    public String update(CounselingDTO counselingDTO
                         ,RedirectAttributes redirectAttributes){

        log.info("상담 수정화면 가는 중");
        //시간 확인용
        log.info("counselingDTO= " + counselingDTO);


        counselingService.update(counselingDTO);
        redirectAttributes.addFlashAttribute("result", "updated");
        redirectAttributes.addAttribute("idx", counselingDTO.getIdx());

        log.info("counselingDTO= " + counselingDTO);

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
