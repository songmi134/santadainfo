package com.mountain.mountain.domain.category.service;

import com.mountain.mountain.controller.category.dto.ResponseCategoryDTO;
import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.domain.category.dao.CategoryRepository;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category findCateByNo(RegisterCommuDTO registerCommuDTO) throws CustomException {
        Optional<Category> category = categoryRepository.findById(registerCommuDTO.getCateId());
        if (category.isPresent()) {
            return category.get();
        } throw new CustomException(ErrorCode.NOT_FOUND_CATEGORY);
    }


    public Page<Category> findAllCategory(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
