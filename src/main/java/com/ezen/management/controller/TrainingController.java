package com.ezen.management.controller;

import com.ezen.management.domain.*;
import com.ezen.management.dto.*;
import com.ezen.management.repository.CurriculumRepository;
import com.ezen.management.repository.MemberRepository;
import com.ezen.management.service.MemberService;
import com.ezen.management.service.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/training")
public class TrainingController {

    private final TrainingService trainingService;
    private final MemberService memberService;

    private final CurriculumRepository curriculumRepository;
    private final MemberRepository memberRepository;
    //----------------------------------------------------유형----------------------------------------------------

    //유형전체
    @GetMapping("/category")
    public String categoryIndex(Model model, PageRequestDTO pageRequestDTO){

        PageResponseDTO<Category> responseDTO = trainingService.searchCategory(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
//        List<Category> list =  trainingService.categoryIndex();
//        model.addAttribute("list", list);
    return "training/category/index";
    }

    //유형추가
    @ResponseBody
    @PostMapping(value = "/category/insert")
    public void categoryInsert(@RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) throws BindException {
        trainingService.categoryInsert(categoryDTO);
    }

    //유형수정
    @ResponseBody
    @PutMapping(value = "/category/{idx}")
    public void categoryUpdate(@RequestBody CategoryDTO categoryDTO){
        log.info("Controller input : " + categoryDTO);
        trainingService.categoryUpdate(categoryDTO);
    }

    //유형삭제
    @ResponseBody
    @DeleteMapping("/category/{idx}")
    public void categoryDelete(@PathVariable Long idx){
        trainingService.categoryDelete(idx);
    }

    //----------------------------------------------------과목----------------------------------------------------

    //과목전체 & 페이징
    @GetMapping("/subject")
    public String subjectIndex(Model model, PageRequestDTO pageRequestDTO){

        PageResponseDTO<Subject> responseDTO = trainingService.searchSubject(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
        return "training/subject/index"; }

    //과목추가
    @ResponseBody
    @PostMapping(value = "/subject/insert")
    public void subjectInsert(@RequestBody SubjectDTO subjectDTO){
        trainingService.subjectInsert(subjectDTO);
    }

    //과목수정
    @ResponseBody
    @PutMapping(value = "/subject/{idx}")
    public void subjectUpdate(@RequestBody SubjectDTO subjectDTO){
        trainingService.subjectUpdate(subjectDTO);
    }

    //과목삭제
    @ResponseBody
    @DeleteMapping(value = "/subject/{idx}")
    public void subjectDelete(@PathVariable Long idx){
        trainingService.subjectDelete(idx);
    }



    //----------------------------------------------------과정----------------------------------------------------

    //과정전체
    @GetMapping("/curriculum")
    public String curriculumIndex(Model model, PageRequestDTO pageRequestDTO){
        PageResponseDTO<Curriculum> responseDTO = trainingService.searchCurriculum(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        return "training/curriculum/index";
    }

    //과정추가
    @PostMapping(value = "/curriculum/insert")
    public String curriculumInsert(String name, String category, int time, int day){

        Category c = trainingService.getCategoryIdx(category);

        CurriculumDTO curriculumDTO = new CurriculumDTO();
        curriculumDTO.setName(name);
        curriculumDTO.setCategory(c);
        curriculumDTO.setTime(time);
        curriculumDTO.setDay(day);

        trainingService.curriculumInsert(curriculumDTO);
        return "redirect:/training/curriculum";
    }

    //과정수정
    @PostMapping(value = "/curriculum/update")
    public String  curriculumUpdate(Long idx, String name, String category_name, Long category_idx, int time, int day){

        Category category = trainingService.getCategoryIdx(category_name);

        CurriculumDTO curriculumDTO = new CurriculumDTO();
        curriculumDTO.setIdx(idx);
        curriculumDTO.setName(name);
        curriculumDTO.setCategory(category);
        curriculumDTO.setTime(time);
        curriculumDTO.setDay(day);

        trainingService.curriculumUpdate(curriculumDTO);
        return "redirect:/training/curriculum";
    }

    //과정삭제
    @ResponseBody
    @DeleteMapping(value = "/curriculum/{idx}")
    public void curriculumDelete(@PathVariable Long idx){
        trainingService.curriculumDelete(idx);
    }

    //----------------------------------------------------수업----------------------------------------------------

    //수업전체
    @GetMapping("/lesson")
    public String lessonIndex(Model model, PageRequestDTO pageRequestDTO){

        PageResponseDTO<Lesson> responseDTO = trainingService.searchLesson(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        PageResponseDTO<Curriculum> responseCurriculum = trainingService.searchCurriculum(pageRequestDTO);
        List<Curriculum> curriculum = responseCurriculum.getDtoList();
        model.addAttribute("curriculum", curriculum);

        PageResponseDTO<Member> responseMember = memberService.findAll(pageRequestDTO);
        List<Member> member = responseMember.getDtoList();
        model.addAttribute("member", member);

        log.info("Controller responseDTO : " + responseDTO);
        log.info("Controller curriculum : " + curriculum);

        return "training/lesson/index";
    }

    //수업상세
    //수업유형, 수업과정, 수업세부정보 -> 클릭으로 해당 수업의 학생들까지 ㄱㄱ


    //수업추가
    @PostMapping(value = "/lesson/insert")
    public String lessonInsert(Long curriculum_idx, String member_id, String classRoom, int number, LocalDate startDay, LocalDate endDay, LocalDate survey1,LocalDate survey2,LocalDate survey3, String content){

        Optional<Curriculum> curriculumResult = curriculumRepository.findById(curriculum_idx);
        Curriculum curriculum = curriculumResult.orElseThrow();

        Optional<Member> memberResult = memberRepository.findById(member_id);
        Member member = memberResult.orElseThrow();

        LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setCurriculum_idx(curriculum.getIdx());
            lessonDTO.setCurriculum_name(curriculum.getName());
            lessonDTO.setCurriculum_time(curriculum.getTime());
            lessonDTO.setCurriculum_day(curriculum.getDay());
            lessonDTO.setMember_id(member.getId());
            lessonDTO.setMember_name(member.getName());
            lessonDTO.setNumber(number);
            lessonDTO.setStartDay(startDay);
            lessonDTO.setEndDay(endDay);
            lessonDTO.setSurvey1(survey1);
            lessonDTO.setSurvey2(survey2);
            lessonDTO.setSurvey3(survey3);
            lessonDTO.setClassRoom(classRoom);
            lessonDTO.setContent(content);

        trainingService.lessonInsert(lessonDTO);

        return "redirect:/training/lesson";
    }

    //수업수정

    //수업삭제

}
