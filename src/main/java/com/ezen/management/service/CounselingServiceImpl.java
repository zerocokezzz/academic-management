package com.ezen.management.service;


import com.ezen.management.domain.Counseling;
import com.ezen.management.domain.Student;
import com.ezen.management.dto.CounselingDTO;
import com.ezen.management.dto.CounselingStudentDTO;
import com.ezen.management.dto.PageRequestDTO;
import com.ezen.management.dto.PageResponseDTO;
import com.ezen.management.repository.CounselingRepository;
import com.ezen.management.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CounselingServiceImpl implements CounselingService {

    @Autowired
    private final CounselingRepository counselingRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private  final StudentRepository studentRepository;

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
    public CounselingStudentDTO detail(Long idx) {

        Optional<Counseling> result = counselingRepository.findById(idx);
        Counseling counseling = result.orElseThrow();

        // Counseling 객체에서 studentIdx 가져오기
        Long studentIdx = counseling.getStudent().getIdx();

        // studentIdx를 사용하여 해당 학생 조회
        Optional<Student> studentResult = studentRepository.findById(studentIdx);
        Student student = studentResult.orElseThrow();

        //웩 수동매핑
        CounselingStudentDTO counselingStudentDTO = CounselingStudentDTO.builder()
                        .counselingIdx(counseling.getIdx())
                        .studentIdx(student.getIdx())
                        .name(student.getName())
                        .fileName(student.getFileName())
                        .phone(student.getPhone())
                        .counselingDate(counseling.getCounselingDate())
                        .content(counseling.getContent())
                        .method(counseling.getMethod())
                        .modDate(counseling.getModDate())
                        .regDate(counseling.getRegDate())
                        .writer(counseling.getWriter())
                        .round(counseling.getRound())
                        .email(student.getEmail())
                        .build();

        log.info("counselingStudentDTO= " + counselingStudentDTO);

        return counselingStudentDTO;

    }



    @Override
    public Long insert(CounselingDTO counselingDTO) {

        Counseling counseling = modelMapper.map(counselingDTO, Counseling.class);
        log.info("counseling= " + counseling);

        //counselingRepository.save(counseling).getIdx();

        Long idx = (long) counselingRepository.save(counseling).getIdx();
        log.info("idx= " + idx);

        return idx;
    }




    @Override
    public void update(CounselingStudentDTO counselingStudentDTO) {

        Optional<Counseling> result = counselingRepository.findById(counselingStudentDTO.getCounselingIdx());

        Counseling counseling = result.orElseThrow();

        //Entity에 추가함
        counseling.changeContent(counselingStudentDTO.getRound()
                ,counselingStudentDTO.getContent()
                ,counselingStudentDTO.getMethod()
                ,counselingStudentDTO.getWriter());
        
        counselingRepository.save(counseling);

    }




    @Override
    public void delete(Long idx) {

        counselingRepository.deleteById(idx);
    }


    @Override
    public Counseling findById(Long studentIdx) {

         Optional<Counseling> studentById = counselingRepository.findById(studentIdx);

         if(studentById.isPresent()){
            //실제 객체를 가져오기 위해 .get() 사용
             Counseling counseling = studentById.get();
             log.info("studentById= " , studentById);
             return counseling;

         }else {

             // 예외처리가 들어갈 구간

             return null;
         }


    }


    //목록으로 학생정보 가져오기
    @Override
    public List<Counseling> findByStudentIdx(Long studentIdx) {


        List<Counseling> counselingList = counselingRepository.findByStudentIdx(studentIdx);
        log.info("counselingList= " + counselingList);


        return counselingList;
    }



}
