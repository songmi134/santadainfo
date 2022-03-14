package com.mountain.mountain.controller.category;


import com.mountain.mountain.controller.category.dto.ResponseCategoryDTO;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    //카테고리 조회
    @GetMapping("")
    public Page<ResponseCategoryDTO> getCateList(Pageable pageable){
        return categoryService.findAllCategory(pageable).map(Category -> new ResponseCategoryDTO(Category));
    }

}
