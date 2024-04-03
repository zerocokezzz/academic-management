package com.ezen.management.repository.search;

import com.ezen.management.domain.Category;
import com.ezen.management.domain.QCategory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CategorySearchImpl extends QuerydslRepositorySupport implements CategorySearch{
    public CategorySearchImpl() {
        super(Category.class);
    }

    @Override
    public Page<Category> searchCategory(String keyward, Pageable pageable) {

        QCategory category = QCategory.category;

        JPQLQuery<Category> query = from(category);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(keyward != null){
            booleanBuilder.or(category.name.contains(keyward));
            query.where(booleanBuilder);
        }
        
        //페이징
        this.getQuerydsl().applyPagination(pageable, query);

        List<Category> list = query.fetch();
        long count = query.fetchCount();
        
        return new PageImpl<>(list, pageable, count);
    }
}
