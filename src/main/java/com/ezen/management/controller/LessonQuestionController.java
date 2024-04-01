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

    @GetMapping("")
    public String index(Model model, PageRequestDTO pageRequestDTO){

        PageResponseDTO<QuestionAnswer> pageResponseDTO = questionAnswerService.findAll(pageRequestDTO.getKeyword(), pageRequestDTO);

        log.info("pageResponseDTO " + pageResponseDTO);
        log.info("pageResponseDTO.getDtoList() " + pageResponseDTO.getDtoList());

        List<Lesson> lessonList = lessonService.findAll();


        model.addAttribute("pageResponseDTO", pageResponseDTO);
        model.addAttribute("lessonList", lessonList);

        log.info("lessonList : {}", lessonList);


        return "/member/lesson/question";

    }

    @GetMapping("/list")
    @ResponseBody
    public List<QuestionAnswer> list(int lessonIdx){

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        if(lessonIdx == 0){

            PageResponseDTO<QuestionAnswer> all = questionAnswerService.findAll(null, pageRequestDTO);
            return all.getDtoList();
        }

        List<QuestionAnswer> byLesson = questionAnswerService.findByLesson(lessonIdx);

        log.info("questionAnswer by lesson : {} ", byLesson);

        return byLesson;
    }
}
