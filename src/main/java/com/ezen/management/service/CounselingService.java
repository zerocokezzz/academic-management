package com.ezen.management.service;

import com.ezen.management.domain.Counseling;
import com.ezen.management.dto.CounselingDTO;
import com.ezen.management.dto.CounselingStudentDTO;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;

import java.util.List;


public interface CounselingService {
    
    PageResponseDTO<Counseling> counselingList(PageRequestDTO pageRequestDTO);     //전체목록
    CounselingStudentDTO detail(Long idx);              //조회하기
    Long insert(CounselingDTO counselingDTO);           //추가하기
    void update(CounselingDTO counselingDTO);           //수정하기
    void delete(Long idx);                              //삭제하기


    Counseling findById(Long studentIdx);               //학생에 단일 상담정보 가져오기
    Counseling findByidx(Long idx);                     //상담정보 조회
    List<Counseling> findByStudentIdx(Long studentIdx); //학생정보에 전체 상담 정보 가져오기
    
    
    
//  Detail에 사용할 용도이나 아직,,
//    Counseling getCounselingWithStudentId(Student student, Long studentIdx);
    
    
    
    
}
