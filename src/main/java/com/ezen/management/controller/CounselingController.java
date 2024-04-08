package com.ezen.management.controller;

import com.ezen.management.domain.Counseling;
import com.ezen.management.domain.Student;
import com.ezen.management.dto.*;
import com.ezen.management.service.CounselingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequestMapping("/counseling")
public class CounselingController {

    @Autowired
    private final CounselingService counselingService;


    public CounselingController(CounselingService counselingService) {
        this.counselingService = counselingService;
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

//        Counseling counselingWithStudentId = counselingService.getCounselingWithStudentId(student, student.getIdx());
//        Counseling counseling = counselingWithStudentId;
//
//        model.addAttribute("counseling", counseling);
//        log.info("counseling= " + counseling);

    }

//    @GetMapping("/detail/{studentIdx}")
//    public void getCounselingDetail(@PathVariable Long studentIdx, Model model) {
//
//        //List<CounselingDTO> counselingDTOList = (List<CounselingDTO>) counselingService.getCounselingDetailByStudentIdx(studentIdx);
//        List<CounselingDTO> counselingDTOList = counselingService.getCounselingListByStudentIdx(studentIdx);
//
//        model.addAttribute("dto", counselingDTOList);
//        log.info("counselingDTOList= " + counselingDTOList);
//
//    }


    //추가하기
    @GetMapping("/insert")
    public void insert(Model model, CounselingDTO counselingDTO){

        log.info("counseling insert gogo");
    }
    @PostMapping("/insert")
    public String insert(CounselingDTO counselingDTO
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes){

        log.info("-----Counseling Post insert----");

        if(bindingResult.hasErrors()){
            log.info("-----has errors-----");
            redirectAttributes.addFlashAttribute("errors", bindingResult.hasErrors());

            return "redirect:/counseling/insert";
        }


        log.info("counselingDTO= " + counselingDTO);
        Long idx = counselingService.insert(counselingDTO);
        redirectAttributes.addFlashAttribute("result", idx);

        return "redirect:/coundeling/list";

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

}
