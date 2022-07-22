package com.applicationAPI.repository;

import com.applicationAPI.repository.entities.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {
}
