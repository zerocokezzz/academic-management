package com.ezen.management.repository;

import com.ezen.management.domain.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void insertCategory(){
        Category category = Category.builder()
                .name("KDT")
                .build();

        categoryRepository.save(category);
    }
}
