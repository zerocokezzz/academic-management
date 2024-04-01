package com.ezen.management.controller;

import com.ezen.management.domain.Question;
import com.ezen.management.domain.QuestionKeyword;
import com.ezen.management.dto.QuestionDTO;
import com.ezen.management.service.QuestionKeywordService;
import com.ezen.management.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/member/question")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('MASTER', 'ADMIN', 'TEACHER')")
public class QuestionController {

    private final QuestionKeywordService questionKeywordService;
    private final QuestionService questionService;

    @GetMapping("")
    public String index(Model model, String keyword){

        List<QuestionKeyword> questionKeywordList = questionKeywordService.findAll();

        model.addAttribute("questionKeywordList", questionKeywordList);

        questionKeywordList.forEach(questionKeyword -> {
            log.info(questionKeyword.getName());
        });

//        모든 사전평가 문제 리스트
//        여기는 페이징 필요함(3/29)
        List<Question> questions = questionService.findAll();

        questions.forEach(question -> {
            log.info(question.getContent());
        });


        model.addAttribute("questions", questions);

        return "member/question/index";
    }



//    문제 이름으로 문제 리스트 불러와서 body에 리턴함
    @GetMapping("/list")
    @ResponseBody
    public List<Question> questionList(String name){

        log.info("parameter name : {}", name);
        List<Question> questionByName = questionService.findQuestionByName(name);
//
//        questionByName.forEach(question -> {
//            log.info(question.getContent());
//        });

        return questionByName;
    }


    @GetMapping("/getQuestion")
    @ResponseBody
    public Question getQuestion(int questionIdx){

        log.info("questionIdx : {}", questionIdx);
        log.info("question : {}", questionService.findById(questionIdx));

        return questionService.findById(questionIdx);
    }
//
//    @GetMapping("/insert")
//    public String insertGET(){
//        return "/member/question/insert";
//    }
//
//

    @PostMapping("/insert")
    @ResponseBody
    public List<Question> insertPOST(QuestionDTO questionDTO, Model model, String keyword){

        log.info("insert question : {}", questionDTO);

        int result = questionService.insert(questionDTO);

        log.info("parameter name : {}", questionDTO.getName());
        List<Question> questionByName = questionService.findQuestionByName(questionDTO.getName());

        log.info("insertPOST result : {}", questionByName);

        return questionByName;
    }

    @PostMapping("/update")
    @ResponseBody
    public List<Question> updatePost(QuestionDTO questionDTO){
//        해당 문제 수정한 다음 그 문제 키워드로 가져온 문제 리스트 반환

        int update = questionService.update(questionDTO);
        String name = questionDTO.getName();

        List<Question> questionByName = questionService.findQuestionByName(name);

        log.info("update result");
        log.info("" + questionByName);

        return questionByName;


    }


}
