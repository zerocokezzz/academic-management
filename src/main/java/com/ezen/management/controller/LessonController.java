package com.ezen.management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/lesson")
public class LessonController {

    @GetMapping("")
    public String lesson(){
        return "/lesson/index";
    }
}
