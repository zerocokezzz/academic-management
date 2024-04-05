package com.ezen.management.service;


import com.ezen.management.domain.Counseling;
import com.ezen.management.dto.CounselingDTO;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.repository.CounselingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CounselingServiceImpl implements CounselingService {

    private final CounselingRepository counselingRepository;
    private final ModelMapper modelMapper;


    @Override
    public PageResponseDTO<Counseling> counselingList(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("idx");

        Page<Counseling> result = counselingRepository.searchAll(types, keyword, pageable);
        List<Counseling> dtoList = result.getContent().stream()
                .map(counseling -> modelMapper.map(counseling, Counseling.class)).collect(Collectors.toList());

        return PageResponseDTO.<Counseling>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalPages())
                .build();

    }




    @Override
    public CounselingDTO detail(Long idx) {

        Optional<Counseling> result = counselingRepository.findById(idx);
        Counseling counseling = result.orElseThrow();
        CounselingDTO counselingDTO = modelMapper.map(counseling, CounselingDTO.class);
        log.info("counselingDTO= " + counselingDTO);
        return counselingDTO;
    }

    @Override
    public Counseling findById(Long studentIdx) {

        Optional<Counseling> findBystudentId = Optional.ofNullable(counselingRepository.findByStudentIdx(studentIdx));

        return findBystudentId.get();
    }


//    public CounselingDTO getCounselingDetailByStudentIdx(Long studentIdx) {
//        Counseling counseling = counselingRepository.findByStudentIdx(studentIdx); // 해당 학생의 상담 정보 조회
//        // Counseling 객체를 CounselingDTO로 변환
//        CounselingDTO counselingDTO = counselingDTO(counseling);
//
//        return counselingDTO;
//    }
//
//    private CounselingDTO counselingDTO(Counseling counseling) {
//        return CounselingDTO.builder()
//                .idx(counseling.getIdx())
//                .studentIdx(counseling.getStudent().getIdx())
//                .counselingDate(counseling.getCounselingDate())
//                .content(counseling.getContent())
//                .method(counseling.getMethod())
//                .writer(counseling.getWriter())
//                .regDate(counseling.getRegDate())
//                .modDate(counseling.getModDate())
//                .build();
//    }



//    public List<CounselingDTO> getCounselingListByStudentIdx(Long studentIdx) {
//        List<Counseling> counselingList = counselingRepository.findByStudentIdx(studentIdx);
//        return counselingList.stream()
//                .map(this::counselingDTO)
//                .collect(Collectors.toList());
//    }
//    private CounselingDTO counselingDTO(Counseling counseling) {
//        return CounselingDTO.builder()
//                .idx(counseling.getIdx())
//                .studentIdx(counseling.getStudent().getIdx())
//                .counselingDate(counseling.getCounselingDate())
//                .content(counseling.getContent())
//                .method(counseling.getMethod())
//                .writer(counseling.getWriter())
//                .regDate(counseling.getRegDate())
//                .modDate(counseling.getModDate())
//                .build();
//    }



    @Override
    public Long insert(CounselingDTO counselingDTO) {

        Counseling counseling = modelMapper.map(counselingDTO, Counseling.class);
        log.info("counseling= " + counseling);

        Long idx = (long) counselingRepository.save(counseling).getIdx();
        log.info("idx= " + idx);


        return idx;
    }




    @Override
    public void update(CounselingDTO counselingDTO) {

        Optional<Counseling> result = counselingRepository.findById(counselingDTO.getIdx());

        Counseling counseling = result.orElseThrow();

        //Entity에 추가함
        counseling.changeContent(counselingDTO.getContent()
                ,counselingDTO.getMethod());
        
        counselingRepository.save(counseling);

    }




    @Override
    public void delete(Long idx) {

        counselingRepository.deleteById(idx);
    }



}
