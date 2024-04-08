package com.ezen.management.repository.search;


import com.ezen.management.domain.Counseling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounselingSearch {

    Page<Counseling> searchAll(String[] types, String keyword, Pageable pageable);


}
