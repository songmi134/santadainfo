package com.mountain.mountain.domain.category.dao;

import com.mountain.mountain.domain.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
