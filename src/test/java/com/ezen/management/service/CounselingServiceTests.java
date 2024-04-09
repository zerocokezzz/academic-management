package com.ezen.management.service;


import com.ezen.management.domain.Counseling;
import com.ezen.management.domain.Student;
import com.ezen.management.dto.CounselingDTO;
import com.ezen.management.dto.CounselingStudentDTO;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
public class CounselingServiceTests {

    @Autowired
    private CounselingService counselingService;
//    @Autowired
//    private Student student;

    //학생이 추가되면 자동으로 추가되어야 함 + 임시적으로 담당교사가 담당자로 들어가게. + student에서 증가하게(댓글 흠)
    //insert Test
    @Test
    public void testService(){
        log.info("counselingService.getClass().getName()= " + counselingService.getClass().getName());

        //상담시간
        LocalDateTime start = LocalDateTime.now();


        CounselingDTO counselingDTO = CounselingDTO.builder()
                .studentIdx(1L)
                .counselingDate(start)
                .content("아주 훌륭해요")
                .method(1)
                .writer("고흥빵")
                .build();

        Long idx = counselingService.insert(counselingDTO);

        log.info("idx= " + idx);


    }



    @Test
    public void testUpdate(){

        CounselingDTO counselingDTO = CounselingDTO.builder()
                .idx(2L)
                .content("잘해내고 있다는 용기의 드래곤을 줌")
                .method(0)
                .build();

        counselingService.update(counselingDTO);
        log.info("counselingDTO= " + counselingDTO);

    }


    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcwn")
                .keyword("1")
                .page(1)
                .size(1)
                .build();

        PageResponseDTO<Counseling> responseDTO = counselingService.counselingList(pageRequestDTO);

        log.info("responseDTO= " + responseDTO);
    }


}
