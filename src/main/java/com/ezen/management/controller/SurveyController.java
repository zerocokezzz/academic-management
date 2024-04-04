package com.ezen.management.controller;

import com.ezen.management.domain.Student;
import com.ezen.management.dto.StudentDTO;
import com.ezen.management.dto.SurveyDTO;
import com.ezen.management.dto.SurveyDtoList;
import com.ezen.management.service.StudentService;
import com.ezen.management.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
//@RequestMapping //나중에 어떻게할지 생각해보자
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    private final StudentService studentService;

    /*=========================설문관리 CRUD=========================*/

    /**
     * OK
     */
    @GetMapping("member/survey/list")
    public String surveyList(Model model){

        List<SurveyDTO> surveyList = surveyService.surveyList();

        model.addAttribute("surveyList", surveyList);

        return "member/survey/list";
    }

    /**
     * 템플릿 오류 해결해야 함. 경로 문제 같은데...
     */
    @GetMapping("/member/survey/register")
    public String registerGet(Model model, @RequestParam("round")int round){

        return "/member/survey/register";
    }

    /**
     * OK
     */
    @PostMapping("/member/survey/register")
    public String registerPost(@ModelAttribute("surveyDtoList") SurveyDtoList surveyDtoList, @RequestParam("round")int round){

        round = surveyService.register(surveyDtoList);

        return "redirect:/member/survey/read?round=" + round;
    }

    /**
     * OK
     */
    @GetMapping("/member/survey/read")
    public String read(Model model, @RequestParam("round")int round) {

        // 특정 회차(round)에 대한 Survey 목록을 조회합니다.
        List<SurveyDTO> surveyList = surveyService.readAllByRound(round);

        // 조회된 Survey 목록을 모델에 추가합니다.
        model.addAttribute("surveys", surveyList);

        // 뷰 페이지로 이동합니다.
        return "/member/survey/read";
    }

    /**
     * OK
     */
    @GetMapping("/member/survey/remove")
    public String remove(@RequestParam("round")int round) {

        int result = surveyService.deleteAllByRound(round);

        return "redirect:/member/survey/list";
    }

    @GetMapping("/member/survey/modify")
    public String modifyGet(Model model, @RequestParam("round")int round){

        // 특정 회차(round)에 대한 Survey 목록을 조회합니다.
        List<SurveyDTO> surveyList = surveyService.readAllByRound(round);

        // 조회된 Survey 목록을 모델에 추가합니다.
        model.addAttribute("surveys", surveyList);

        return "/member/survey/modify";
    }

    @PostMapping("/member/survey/modify")
    public String modifyPost(@ModelAttribute("surveyDtoList") SurveyDtoList surveyDtoList, @RequestParam("round")int round){

        round = surveyService.modify(surveyDtoList, round);

        return "redirect:/member/survey/read?round=" + round;
    }

    /*=========================설문작성=========================*/

    @PostMapping("/student/survey")
    public String survey(Model model, StudentDTO studentDTO){

        Student student = studentService.findByLessonIdxAndName(studentDTO.getLessonIdx(), studentDTO.getName());

        model.addAttribute("student", student);

        return "/student/survey";
    }
}
