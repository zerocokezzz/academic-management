package com.ezen.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/member/login")
    public void loginGET(String error, String logout){
        log.info("Member login get......");
    }


    @PreAuthorize("hasAnyRole('MASTER', 'ADMIN', 'TEACHER')")
    @GetMapping("/member")
    public String memberIndex(){
        return "/member/index";
    }



}
