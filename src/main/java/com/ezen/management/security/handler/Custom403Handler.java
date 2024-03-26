package com.ezen.management.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class Custom403Handler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("---------------------ACCESS DINIED-----------------------");

        response.setStatus(HttpStatus.FORBIDDEN.value());
//        response.sendRedirect("/member/login?error=ACCESS_DENIED");

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        PrintWriter w = response.getWriter();
        w.println("<script> alert('접근 권한이 없습니다.');");
        w.println("history.go(-1); </script>");
        w.close();

    }




}
