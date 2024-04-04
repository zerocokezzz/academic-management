package com.ezen.management.service;

import com.ezen.management.domain.Counseling;
import com.ezen.management.dto.CounselingDTO;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;


public interface CounselingService {
    
    PageResponseDTO<Counseling> counselingList(PageRequestDTO pageRequestDTO);                //전체목록
//    List<CounselingDTO>studentList();                   //학생목록
    CounselingDTO detail(Long idx);                      //조회하기
    Long insert(CounselingDTO counselingDTO);           //추가하기
    void update(CounselingDTO counselingDTO);           //수정하기
    void delete(Long idx);                               //삭제하기



}
