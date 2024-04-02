package com.ezen.management.service;

import com.ezen.management.domain.Category;
import com.ezen.management.domain.Curriculum;
import com.ezen.management.domain.Subject;
import com.ezen.management.dto.*;
import com.ezen.management.repository.CategoryRepository;
import com.ezen.management.repository.CurriculumRepository;
import com.ezen.management.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TrainingServiceImpl implements TrainingService{

    private final CategoryRepository categoryRepository;
    private final SubjectRepository subjectRepository;
    private final CurriculumRepository curriculumRepository;

    //----------------------------------------------------유형----------------------------------------------------
    //유형전체
    //유형 페이징 & 검색
    @Override
    public PageResponseDTO<Category> searchCategory(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable();

        String keyword = pageRequestDTO.getKeyword();

        Page<Category> categoryPage = categoryRepository.searchCategory(keyword, pageable);

        List<Category> dtoList = categoryPage.getContent();

        return PageResponseDTO.<Category>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)categoryPage.getTotalElements())
                .build();
    }

    // 유형 추가
    @Override
    public void categoryInsert(CategoryDTO categoryDTO) {
        Category category = dtoToEntity(categoryDTO);
        categoryRepository.save(category);
    }

    //유형 수정
    @Override
    public void categoryUpdate(CategoryDTO categoryDTO) {
        Optional<Category> result = categoryRepository.findById(categoryDTO.getIdx());
        Category category = result.orElseThrow();
        category.changeName(categoryDTO.getName());
        categoryRepository.save(category);
    }

    //유형 삭제
    @Override
    public void categoryDelete(Long idx) {
        categoryRepository.deleteById(idx);
    }

    //유형 찾기
    @Override
    public Category getCategoryIdx(String name){
        log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Optional<Category> result = categoryRepository.getCategoryIdx(name);
        Category category = result.orElseThrow();

        log.info("Service Category : " + category);

        return category;
    }

    //----------------------------------------------------과목----------------------------------------------------
    //과목 전체 & 페이징
    @Override
    public PageResponseDTO<Subject> searchSubject(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable();

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Page<Subject> subjectPage = subjectRepository.searchSubject(types, keyword, pageable);

        List<Subject> dtoList = subjectPage.getContent();

        return PageResponseDTO.<Subject>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)subjectPage.getTotalElements())
                .build();
    }

    //과목 등록
    @Override
    public void subjectInsert(SubjectDTO subjectDTO) {
        Subject subject = subjectDtoToEntity(subjectDTO);
        subjectRepository.save(subject);
    }
    
    //과목 수정
    @Override
    public void subjectUpdate(SubjectDTO subjectDTO) {
        Optional<Subject> result = subjectRepository.findById(subjectDTO.getIdx());
        Subject subject = result.orElseThrow();
        subject.changeMethod(subjectDTO.getMethod());
        subjectRepository.save(subject);
    }

    //과목 삭제
    @Override
    public void subjectDelete(Long idx) {
        subjectRepository.deleteById(idx);
    }

    //----------------------------------------------------과정----------------------------------------------------
    //과정 전체
    //과정 페이징 & 검색
    @Override
    public PageResponseDTO<Curriculum> searchCurriculum(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable();

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Page<Curriculum> curriculumPage = curriculumRepository.searchCurriculum(types, keyword, pageable);

        List<Curriculum> dtoList = curriculumPage.getContent();

        return PageResponseDTO.<Curriculum>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)curriculumPage.getTotalElements())
                .build();
    }

    //과정 등록
    @Override
    public void curriculumInsert(CurriculumDTO curriculumDTO) {
        log.info("Service Insert : " + curriculumDTO);

        Curriculum curriculum = curriculumDtoTOEntity(curriculumDTO);
        curriculumRepository.save(curriculum);
    }

    //과정 수정
    @Override
    public void curriculumUpdate(CurriculumDTO curriculumDTO) {
        Optional<Curriculum> result = curriculumRepository.findById(curriculumDTO.getIdx());
        Curriculum curriculum = result.orElseThrow();

        log.info("Service Entity : " + curriculum);
        log.info("Service DTO : " + curriculumDTO);

            curriculum.changeName(curriculumDTO.getName());
            curriculum.changeCategory(curriculumDTO.getCategory());
            curriculum.changeDay(curriculumDTO.getDay());
            curriculum.changeTime(curriculumDTO.getTime());

            curriculumRepository.save(curriculum);
    }

    //과정 삭제
    @Override
    public void curriculumDelete(Long idx) {
        curriculumRepository.deleteById(idx);
    }


}
