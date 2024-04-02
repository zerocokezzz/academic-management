package com.ezen.management.controller;

import com.ezen.management.domain.Question;
import com.ezen.management.domain.QuestionName;
import com.ezen.management.dto.QuestionBlockDTO;
import com.ezen.management.dto.QuestionDTO;
import com.ezen.management.service.QuestionNameService;
import com.ezen.management.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/member/question")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('MASTER', 'ADMIN', 'TEACHER')")
public class QuestionController {

    private final QuestionNameService questionNameService;
    private final QuestionService questionService;

    @GetMapping("")
    public String index(Model model, String keyword){

        List<QuestionName> questionKeywordList = questionNameService.findAll();

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
    public List<Question> updatePOST(QuestionDTO questionDTO){
//        해당 문제 수정한 다음 그 문제 키워드로 가져온 문제 리스트 반환

        int update = questionService.update(questionDTO);
        String name = questionDTO.getName();

        List<Question> questionByName = questionService.findQuestionByName(name);

        log.info("update result");
        log.info("" + questionByName);

        return questionByName;


    }

    @PostMapping("/delete")
    @ResponseBody
    public List<Question> deletePOST(int questionIdx){

        log.info("questionIdx : {}", questionIdx);
        log.info("question : {}", questionService.findById(questionIdx));

        String name = questionService.findById(questionIdx).getName();

        questionService.delete(questionIdx);

        return questionService.findQuestionByName(name);


    }

    @GetMapping("/create")
    public void createGET(){

    }

    @PostMapping("/create")
    public String createPOST(String name,
                             @RequestParam("number") List<Integer> number,
                             @RequestParam("content") List<String> content,
                             @RequestParam(value = "example", required = false) List<String> example,
                             @RequestParam("item1") List<String> item1,
                             @RequestParam("item2") List<String> item2,
                             @RequestParam("item3") List<String> item3,
                             @RequestParam("item4") List<String> item4,
                             @RequestParam("answer") List<Integer> answer){

        log.info("!!!!!!!!!!!!!!!!!!! createPOST! !!!!!!!!!!!!!!!!!!!");

        log.info("number : {}", number);
        log.info("content : {}", content);
        log.info("answer : {}", answer);
//
//        List<QuestionDTO> questionDTOList = new ArrayList<>();
//
//        for(int i = 0; i < content.size(); i++){
//            QuestionDTO questionDTO = QuestionDTO.builder()
//                    .name(name)
//                    .number(number.get(i))
//                    .content(content.get(i))
//                    .item1(item1.get(i))
//                    .item2(item2.get(i))
//                    .item3(item3.get(i))
//                    .item4(item4.get(i))
//                    .answer(answer.get(i))
//                    .build();
//
//            if(example != null && example.get(i) != null){
//                questionDTO.setExample(example.get(i));
//            }
//
//            questionDTOList.add(questionDTO);
//        }
//
//        questionDTOList.forEach(questionDTO -> {
//            log.info("questionDTO : {}", questionDTO);
//        });

//
//        questionBlockDTO.getQuestionDTOList().forEach(questionDTO -> {
//            log.info("questionDTO : {}", questionDTO);
//        });

        return null;
    }






}
