package com.ezen.management.service;

import com.ezen.management.domain.Category;
import com.ezen.management.domain.Curriculum;
import com.ezen.management.domain.Subject;
import com.ezen.management.dto.*;

import java.util.List;

public interface TrainingService {

    //----------------------------------------------------유형----------------------------------------------------
    //유형 전체
    //유형 페이징
    PageResponseDTO<Category> searchCategory(PageRequestDTO pageRequestDTO);

    //유형 등록
    public void categoryInsert(CategoryDTO categoryDTO);

    //유형 수정
    public void categoryUpdate(CategoryDTO categoryDTO);

    //유형 삭제
    public void categoryDelete(Long idx);

    //유형 찾기
    public Category getCategoryIdx(String name);

    default Category dtoToEntity(CategoryDTO categoryDTO){

        Category category = Category.builder()
                .idx(categoryDTO.getIdx())
                .name(categoryDTO.getName())
                .build();

        return category;
    }

    //----------------------------------------------------과목----------------------------------------------------
    //과목 전체
    //과목 페이징
    public PageResponseDTO<Subject> searchSubject(PageRequestDTO pageRequestDTO);

    //과목 등록
    public void subjectInsert(SubjectDTO subjectDTO);

    //과목 수정
    public void subjectUpdate(SubjectDTO subjectDTO);

    //과목 삭제
    public void subjectDelete(Long idx);

    default Subject subjectDtoToEntity(SubjectDTO subjectDTO){

        Subject subject = Subject.builder()
                .name(subjectDTO.getName())
                .method(subjectDTO.getMethod())
                .build();

        return subject;
    }

    //----------------------------------------------------과정----------------------------------------------------
    //과정 전체 & 페이징 & 검색
    public PageResponseDTO<Curriculum> searchCurriculum(PageRequestDTO pageRequestDTO);

    //과정 등록
    public void curriculumInsert(CurriculumDTO curriculumDTO);

    //과정 수정
    public void curriculumUpdate(CurriculumDTO curriculumDTO);

    //과정 삭제
    public void curriculumDelete(Long idx);

    default Curriculum curriculumDtoTOEntity(CurriculumDTO curriculumDTO){


        Curriculum curriculum = Curriculum.builder()
                .name(curriculumDTO.getName())
                .category(curriculumDTO.getCategory())
                .time(curriculumDTO.getTime())
                .day(curriculumDTO.getDay())
                .build();

        return curriculum;
    }
}