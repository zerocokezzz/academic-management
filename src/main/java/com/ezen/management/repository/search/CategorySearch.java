package com.ezen.management.repository.search;

import com.ezen.management.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategorySearch {

    Page<Category> searchCategory(String keyward, Pageable pageable);

}
