package com.ezen.management.controller;

import com.ezen.management.domain.Lesson;
import com.ezen.management.domain.QuestionAnswer;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.repository.LessonRepository;
import com.ezen.management.repository.QuestionAnswerRepository;
import com.ezen.management.service.LessonService;
import com.ezen.management.service.QuestionAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAnyRole('MASTER', 'ADMIN', 'TEACHER')")
@RequestMapping("/member/lesson/question")
public class LessonQuestionController {

    private final QuestionAnswerService questionAnswerService;
    private final LessonService lessonService;

//    @GetMapping("")
//    public String index(Model model, PageRequestDTO pageRequestDTO){
//
//        PageResponseDTO<QuestionAnswer> pageResponseDTO = questionAnswerService.findAll(pageRequestDTO.getKeyword(), pageRequestDTO);
//
//        log.info("pageResponseDTO " + pageResponseDTO);
//        log.info("pageResponseDTO.getDtoList() " + pageResponseDTO.getDtoList());
//
//        List<Lesson> lessonList = lessonService.findAll();
//
//
//        model.addAttribute("pageResponseDTO", pageResponseDTO);
//        model.addAttribute("lessonList", lessonList);
//
//        log.info("lessonList : {}", lessonList);
//
//
//        return "/member/lesson/question";
//
//    }


    @GetMapping("")
    public String index(Model model, PageRequestDTO pageRequestDTO, @RequestParam(defaultValue = "0") int lessonIdx){

        Lesson lesson = lessonService.findById(lessonIdx);

        PageResponseDTO<QuestionAnswer> pageResponseDTO = questionAnswerService.findAll(lesson, pageRequestDTO.getKeyword(), pageRequestDTO);

        log.info("pageRequestDTO : {} ", pageRequestDTO);
        log.info("pageResponseDTO " + pageResponseDTO);
        log.info("pageResponseDTO.getDtoList() " + pageResponseDTO.getDtoList());

        List<Lesson> lessonList = lessonService.findAll();
//        List<QuestionAnswer> lessonList = pageResponseDTO.getDtoList();

        model.addAttribute("pageResponseDTO", pageResponseDTO);
        model.addAttribute("lessonList", lessonList);

        log.info("lessonList : {}", lessonList);


        return "/member/lesson/question";

    }

    @GetMapping("/list")
    @ResponseBody
    public PageResponseDTO<QuestionAnswer> list(PageRequestDTO pageRequestDTO, @RequestParam(defaultValue = "0") int lessonIdx){

//        PageRequestDTO pageRequestDTO = new PageRequestDTO();

//        if(lessonIdx == 0){
//
//            PageResponseDTO<QuestionAnswer> all = questionAnswerService.findAll(null, pageRequestDTO);
//            return all.getDtoList();
//        }

        log.info("pageRequestDTO : {}", pageRequestDTO);
//        List<QuestionAnswer> dtoList = questionAnswerService.findByLesson(lessonIdx);

        Lesson lesson = lessonService.findById(lessonIdx);
        PageResponseDTO<QuestionAnswer> all = questionAnswerService.findAll(lesson, pageRequestDTO.getKeyword(), pageRequestDTO);

//        List<QuestionAnswer> dtoList = all.getDtoList();
//
//        log.info("questionAnswer by lesson : {} ", dtoList);
//
//        return dtoList;

        return all;
    }
}
