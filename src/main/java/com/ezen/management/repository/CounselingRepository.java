package com.ezen.management.repository;

import com.ezen.management.domain.Counseling;
import com.ezen.management.domain.Student;
import com.ezen.management.dto.CounselingDTO;
import com.ezen.management.dto.StudentDTO;
import com.ezen.management.repository.search.CounselingSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface CounselingRepository extends JpaRepository<Counseling, Long>, CounselingSearch {


    //특정 데이터베이스에서 동작하는 SQL을 사용하는 기능이랍니다 447p
    @Query(value = "select now()", nativeQuery = true)
    String getTime();


    //검색 기능
    //날짜 타입
    @Query("select c from Counseling c where c.counselingDate >= :startDate and c.counselingDate <= :endDate")
    Page<Counseling> findKeyword(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    //숫자타입
    @Query("select c from Counseling c where c.method = :method")
    Page<Counseling> findKeyword(int method, Pageable pageable);
    //문자타입
    @Query("select c from Counseling c where c.writer like concat('%', :keyword, '%')")
    Page<Counseling> findKeyword(String keyword, Pageable pageable);



    //list
    //  @Query("select c from Counseling c")
    //  PageResponseDTO<CounselingDTO> findAllByCounseling(PageRequestDTO pageRequestDTO);
    @Query("select c from Counseling c join Student s on c.student.idx = s.idx where c.student = :student")
    Page<Counseling> getCounselingWithStudentName(Student student, Pageable pageable);



    //detail 학생정보로 조회 / 학생정보를 list로 받아와서 처리해야함 / 자동으로 query 생성하는 JPQL
    List<Counseling> findByStudentIdx(Long studentIdx);


//    @Query("select c from Counseling c join Student s on c.student.idx = s.idx where c.student = :student")
//    Counseling getCounselingWithStudentId(Student student, Long studentIdx);



}
