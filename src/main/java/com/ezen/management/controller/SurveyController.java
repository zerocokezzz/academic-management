package com.ezen.management.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student/surbay")
@Log4j2
public class SurvayController {

    @GetMapping("/list")
    public String list(){

        log.info("뭐지??????????????");

        return "/student/surbay/list";
    }
}
